package org.training.issuetracker.data.hiber;

import java.util.Collections;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.training.issuetracker.domain.Issue;
import org.training.issuetracker.domain.DAO.IssueDAO;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.utils.SearchFilterParams;

public class IssueImplHiber implements IssueDAO {
	
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
		
		List result = hibernateTemplate.findByCriteria(criteria, firstResult, rows);
		return Collections.checkedList(result, Issue.class);
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
