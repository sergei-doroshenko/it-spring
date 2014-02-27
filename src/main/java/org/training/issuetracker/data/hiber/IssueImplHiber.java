package org.training.issuetracker.data.hiber;

import java.util.Collections;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.training.issuetracker.domain.Issue;
import org.training.issuetracker.domain.User;
import org.training.issuetracker.domain.DAO.IssueDAO;
import org.training.issuetracker.exceptions.DaoException;

public class IssueImplHiber implements IssueDAO {
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	
	
	@Override
	public int getIssueRecordsCount() throws DaoException {
		
		return DataAccessUtils.intResult(hibernateTemplate.find("select count(*) from Issue"));
	}

	@Override
	public List getIssueList(User user, int page, int rows, String sidx, String sord) throws DaoException {
		
		DetachedCriteria criteria = DetachedCriteria.forClass(Issue.class);
		int firstResult = (page - 1) * rows;
		
		if (sord.equals("asc")) {
			criteria.addOrder(Order.asc(sidx));
		} else {
			criteria.addOrder(Order.desc(sidx));
		}
		
		List result = hibernateTemplate.findByCriteria(criteria, firstResult, rows);
		return Collections.checkedList(result, Issue.class);
	}

	@Override
	public Issue getIssueById(long id) throws DaoException {
		
		return hibernateTemplate.get(Issue.class, id);
	}

	@Override
	public boolean isId(long id) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long insertIssue(Issue issue) throws DaoException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long updateIssue(Issue issue) throws DaoException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long deleteIssue(long id) throws DaoException {
		// TODO Auto-generated method stub
		return 0;
	}

}
