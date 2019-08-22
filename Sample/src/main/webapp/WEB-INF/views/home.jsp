<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:forEach items="${list}" var="item">
<p>${item.id} / ${item.pw} / ${item.address}</p>
</c:forEach>