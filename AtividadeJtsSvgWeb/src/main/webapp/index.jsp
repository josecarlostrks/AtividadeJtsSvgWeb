<%--! 
    Document   : index
    Created on : 26/11/2018, 21:08:33
    Author     : Matheus
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
	<title>Home</title>
        <link rel="stylesheet" type="text/css" href="css/materialize.css">
</head>
<body class="#f5f5f5 grey lighten-4">

	<div class="row">
 	 	<div class="col s12 #ef5350 red lighten-1">
 	 		<CENTER>
 	 			<h3>
 	 				<b>JTS + SVG</b>
 	 			</h3>
 	 		</CENTER>
 	 	</div>
 	 </div>
 	 <!--Container-->
 	 <div class="container">
 	 		
    		<div class="row" style="margin-top: 10%;">
    			<!--Formulario-->
    			<form class="col s10" method="POST" action="buscar"style=" margin-left: 15%;">

    				<div class="row">
        				<div class="input-field col s5">
          					<input id="estado1" type="text" class="validate" name="estado1">
          					<label for="estado1">Estado 1</label>
        				</div>
        				<div class="input-field col s5">
          					<input id="estado2" type="text" class="validate" name="estado2">
          					<label for="estado2">Estado 2</label>
        				</div>
        			</div>

    				<div class="row">
        				<div class="input-field col s5">
          					<input id="cidade1" type="text" class="validate" name="cidade1">
          					<label for="cidade1">Cidade 1</label>
        				</div>
        				<div class="input-field col s5">
          					<input id="cidade2" type="text" class="validate" name="cidade2">
          					<label for="cidade2">Cidade 2</label>
        				</div>
        				<div class="row">
        					<div class="col s12">
        						<input class="btn" type="submit" value="Buscar" style="margin-left:18%;">
        						
        					</div>
        				</div>
        			
        			</div>

				</form>
    		</div>

    		<div class="row">
    			<!--Divisor de pagina-->		
 	 			<div class="col s12 grey"></div>

 	 		</div>


 	 		<!--Tabela com resultados-->
    		<div class="row" style="margin-top: 5%;">
    			<div class="col s12">
    				<table>
    					<thead>
    						<th>CIDADE 1</th>
    						<th>${c1.nome}</th>
    						<th>CIDADE 2</th>
    						<th>${c2.nome}</th>
    					</thead>
    					<tr>
    						<td>Populacao:</td>
    						<td>${c1.populacao}</td>
    						<td>Populacao:</td>
    						<td>${c2.populacao}</td>
    					</tr>
    					<tr>
    						<td>Densidade demográfica:</td>
    						<td>
                                                    <fmt:formatNumber type="number" minFractionDigits="3" value="${c1.densDemografica}" />
                                                </td>
    						<td>Densidade demográfica:</td>
                                                <td>
                                                    <fmt:formatNumber type="number" minFractionDigits="3" value="${c2.densDemografica}" />
                                                </td>
    					</tr>
    					<tr>
    						<td>Área:</td>
    						<td>
                                                    <fmt:formatNumber type="number" minFractionDigits="3" value="${c1.area}" />
                                                </td>
    						<td>Área:</td>
    						<td>
                                                    <fmt:formatNumber type="number" minFractionDigits="3" value="${c2.area}" />
                                                </td>
    					</tr>
    					<tr>
    						<td>Perímetro:</td>
    						<td>
                                                    <fmt:formatNumber type="number" minFractionDigits="3" value="${c1.perimetro}" />
                                                </td>
    						<td>Perímetro:</td>
    						<td>
                                                    <fmt:formatNumber type="number" minFractionDigits="3" value="${c2.perimetro}" />
                                                </td>
    					</tr>
    				</table>
    			</div>
    		</div>

    		<div class="row">
    			<!--Divisor de pagina-->
    			<div class="col s12 grey"></div>
    		</div>

    		<!--Distâcia entre cidades-->
    		<div class="row">
    			<div class="col center" style="margin-left: 35%; margin-top: 5%">
    				<table>
    					<tr>
    						<th>Distância entre cidades:</th>
    						<td>
                                                    <fmt:formatNumber type="number" minFractionDigits="3" value="${distancia}" />
                                                </td>
    						<td>Km</td>
    					</tr>
    				</table>
    			</div>
    		</div>

    		<div class="row">
    			<!--Divisor de pagina-->
    			<div class="col s12 grey"></div>
    		</div>

    		<!--SVG-->
    		<div class="row">
    			<div class="col">
    				<svg height="800" width="800" viewBox="${viewBox}">
    					<!--Path da Cidade 1-->
  						<path name="${c1.nome}" d="${path1}" stroke="black" stroke-width="0.001" fill="red" fill-opacity="1" onclick="imprimirNome(evt)"/>
  						<!--Path da Cidade 2-->
  						<path name="${c2.nome}" d="${path2}" stroke="black" stroke-width="0.001" fill="green" fill-opacity="1" onclick="imprimirNome(evt)"/>
					</svg>
    			</div>
    		</div>


    	</div>

<script src="js/materialize.js"></script>
<script type="text/javascript">

	function imprimirNome(evt){
		alert(evt.target.getAttribute("name"));
	}

</script>
</body>
</html>
