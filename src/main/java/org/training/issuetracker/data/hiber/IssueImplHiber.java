package org.training.issuetracker.data.hiber;

import java.sql.Date;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.training.issuetracker.domain.Issue;
import org.training.issuetracker.domain.DAO.BuildDAO;
import org.training.issuetracker.domain.DAO.IssueDAO;
import org.training.issuetracker.domain.DAO.ProjectDAO;
import org.training.issuetracker.domain.DAO.PropDAO;
import org.training.issuetracker.domain.DAO.PropertyType;
import org.training.issuetracker.domain.DAO.UserDAO;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.utils.SearchFilter;
import org.training.issuetracker.utils.SearchFilterParams;
import org.training.issuetracker.utils.SearchRule;

import flexjson.JSONDeserializer;

public class IssueImplHiber implements IssueDAO {
	private Logger logger = Logger.getLogger(getClass().getCanonicalName());
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private PropDAO propDAO;
	
	@Autowired
	private ProjectDAO projectDAO;
	
	@Autowired
	private BuildDAO buildDAO;
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public int getIssueRecordsCount() throws DaoException {	
		return DataAccessUtils.intResult(hibernateTemplate.find("select count(*) from Issue"));
	}

	@Override
	public List getIssueList(SearchFilterParams params) throws DaoException {
		
		DetachedCriteria criteria = DetachedCriteria.forClass(Issue.class);
		int page = params.getPage();
		int rows = params.getRows();
		int firstResult = (page - 1) * rows;
		String sord = params.getSord();
		
		if (sord.equals(SearchFilterParams.ASC)) {
			criteria.addOrder(Order.asc(params.getSidx()));
		} else {
			criteria.addOrder(Order.desc(params.getSidx()));
		}
		
		criteria = addSearch(criteria, params.getFilters());
		
		List result = hibernateTemplate.findByCriteria(criteria, firstResult, rows);
		return Collections.checkedList(result, Issue.class);
	}
	
	public Map<String, Set<SearchRule>> checkDoubles (List<SearchRule> rules) {
		Map<String, Set<SearchRule>> checked = new HashMap<String, Set<SearchRule>>();
		for (SearchRule rule : rules) {
			String key = rule.getField();
			
			if(!checked.containsKey(key)){
				checked.put(key, new HashSet<SearchRule>());
			}
			
			checked.get(key).add(rule);

		}
		return checked;
	}
	
	public DetachedCriteria addSearch (DetachedCriteria criteria, String filters) throws NumberFormatException, DaoException {
		if(filters != null && !filters.isEmpty()) {
			logger.debug(filters);
			SearchFilter filter = new JSONDeserializer<SearchFilter>().deserialize(filters, SearchFilter.class);
			logger.debug("Filter " + filter.toString());
			List<SearchRule> rules = filter.getRules();
			Map<String, Set<SearchRule>> checkedRules = checkDoubles(rules);
			
			for (String key : checkedRules.keySet()) {
				Set<SearchRule> rulesSet = checkedRules.get(key);
				boolean isNeedExpr = (rulesSet.size() > 1);
				
				for (SearchRule rule : rulesSet) {
					logger.debug("Rule " + rule);
					String field = rule.getField();
					String data = rule.getData();
					Object value = getSearchValue(field, data);
					switch (rule.getOp()) {
						case "eq" : {
							criteria.add(Restrictions.eq(rule.getField(), value));
							break;
						}
						case "ne" : {
							criteria.add(Restrictions.ne(field, value));
							break;
						}
						case "lt" : {
							criteria.add(Restrictions.lt(field, value));
							break;
						}
						case "le" : {
							criteria.add(Restrictions.le(field, value));
							break;
						}
						case "gt" : {
							criteria.add(Restrictions.gt(field, value));
							break;
						}
						case "ge" : {
							criteria.add(Restrictions.ge(field, value));
							break;
						}
						case "bw" : {
							break;
						}
						case "bn" : {
							break;
						}
						case "ew" : {
							break;
						}
						case "en" : {
							break;
						}
						case "cn" : {
							break;
						}
						case "nc" : {
							break;
						}
						default : {
							break;
						}
					};
				}
			}
		}
		
		return criteria;
	}
	
	public Object getSearchValue (String field, String data) throws NumberFormatException, DaoException {
		Object value = null;
		if (field.equals("id")){
			value = Long.parseLong(data);
		}
		
		if(field.equals("createDate") || field.equals("modifyDate")) {
			value = Date.valueOf(data);
		}
		
		if(field.equals("createBy") || field.equals("modifyBy")
				|| field.equals("assignee")) {
			value = userDAO.getUser(Long.parseLong(data));
		}
		
		if(field.equals("status") || field.equals("type") 
				|| field.equals("resolution") || field.equals("priority")) {
			PropertyType prop = PropertyType.valueOf(field.toUpperCase());
			value = propDAO.getProp(prop, Long.parseLong(data));
		}
		return value;
	}
	
	@Override
	public Issue getIssueById(long id) throws DaoException {
		return hibernateTemplate.get(Issue.class, id);
	}

	@Override
	public long insertIssue(Issue issue) throws DaoException {
		return (Long) hibernateTemplate.save(issue);
	}

	@Override
	public void updateIssue(Issue issue) throws DaoException {
		hibernateTemplate.update(issue);
	}

	@Override
	public void deleteIssue(long id) throws DaoException {
		hibernateTemplate.delete(getIssueById(id));
	}

}
