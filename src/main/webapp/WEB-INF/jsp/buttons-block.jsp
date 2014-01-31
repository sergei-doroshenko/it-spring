<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="buttons-block">
    <div class="buttons-container">
    	<c:choose>
			<c:when test="${(user.role.name eq constants.ROLE_USER) or (user.role.name eq constants.ROLE_ADMIN)}">
				<button id="new-button" class="control-button">New</button>
				<button id="edit-button" class="control-button">Edit</button>
				<button id="save-button" class="control-button">Save</button>
				<button id="cancel-button" class="control-button">Cancel</button>
				<c:if test="${user.role.name eq constants.ROLE_ADMIN}">
					<button id="delete-button" class="control-button">Delete</button>
				</c:if>
			</c:when>
		</c:choose>
    </div>
</div>