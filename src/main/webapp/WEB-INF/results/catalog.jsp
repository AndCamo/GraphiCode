<%@ page import="java.util.List" %>
<%@ page import="model.ProductBean" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title id="page-title">Cataolgo - ${requestScope.filter}</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/main-style.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/style-menu-show.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/catalog-style.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/dashboard-style.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/form-style.css">
  <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/show-page-style.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
  <script src="${pageContext.request.contextPath}/script/navbar-script.js" defer></script>
  <script src="https://kit.fontawesome.com/2f266e86a2.js" crossorigin="anonymous"></script>
  <script src="${pageContext.request.contextPath}/script/validateUserForm.js" defer></script>
  <script>
    function setTitle(){
      let title = document.getElementById("page-title");
      let categoria = getFilter();
      title.innerHTML = "Catalogo - " + categoria;

    }
    function getFilter(){
      return document.getElementById("filter").value;
    }

    function showFilter(){
      $("#filter-container").slideToggle();
    }
  </script>
</head>
<body onload="setTitle()">
<%@ include file="/navbar.jsp"%>
<%
  @SuppressWarnings("unchecked")
  List<String> categories = (List<String>) request.getAttribute("categories");
%>
<div class="main-container">
  <div class="title-container" style="display: block">
    <h1 class="title" style="width: fit-content">Catalogo</h1>
    <p style="width: fit-content; margin: 0 auto" class="form-subtitle" id="filter-label" onclick="showFilter()"> <i class="fa-solid fa-magnifying-glass"></i>  Filtri</p>
    <div id="filter-container">
      <form>
        <label for="filter">Categoria</label>
        <select id="filter">
          <option value="all">Tutte</option>
          <%
            String filter = request.getAttribute("filter").toString();
            for (String category : categories) {
            if (category.equals(filter)){%>
              <option value="<%=category%>" selected><%=category%></option>
          <%} else {%>
            <option value="<%=category%>"><%=category%></option>
          <%}}%>
        </select>
      </form>
      <a onclick="location.href='${pageContext.request.contextPath}/show-catalog?filter=' + getFilter()">Applica</a>
    </div>
  </div>
  <div id="catalog-container">
      <%
        @SuppressWarnings("unchecked")
        List<ProductBean> productList = (List<ProductBean>) request.getAttribute("products");
        if (productList.size() > 0){
          for (int i = 0; i < productList.size(); i++){
      %>
            <div class="product-container">
              <div class="product-image-container">
                <img src="<%=productList.get(i).getImage()%>" alt="<%=productList.get(i).getImage()%>">
              </div>
              <div class="product-info-container">
                <p class="product-category"><%=productList.get(i).getCategory()%></p>
                <p class="product-name"><%=productList.get(i).getName()%></p>
                <p class="product-price"><%=productList.get(i).getPrice()%>€</p>
                <div class="product-button-container">
                  <button class="product-button" type="button" onclick="location.href='${pageContext.request.contextPath}/show-product?productCode=<%=productList.get(i).getCode()%>'">Visualizza</button>
                </div>
              </div>
            </div>
      <%}
        } else { %>
            <div class="title-container" style="margin: 0">
              <h1 class="title">Nessun Prodotto Trovato</h1>
            </div>
        <% } %>
  </div>
</div>
</body>
</html>
