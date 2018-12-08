/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.edu.controle;

import com.ifpb.edu.dao.CidadeDao;
import com.ifpb.edu.dao.GeometriaCidade;
import com.ifpb.edu.model.Cidade;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.io.ParseException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Matheus
 */
@WebServlet(name="buscar", urlPatterns=("/buscar"))
public class BuscarCommand extends HttpServlet{
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        doPost(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        
        String cidade1 = (String)request.getParameter("cidade1");
        String cidade2 = (String)request.getParameter("cidade2");
        String estado1 = (String)request.getParameter("estado1");
        String estado2 = (String)request.getParameter("estado2");
        
        CidadeDao dao = new CidadeDao();
        Cidade c1 = null;
        Cidade c2 = null;
        String codIbgeC1 = null;
        String codIbgeC2 = null;
        String pathC1 = null;
        String pathC2 = null;
        GeometriaCidade geo = new GeometriaCidade(); 
        
        try {
            
            c1 = dao.getCidade(estado1, cidade1);
            c2 = dao.getCidade(estado2, cidade2);
            
            double distancia = geo.getDistancia(c1, c2);
            
            codIbgeC1 = String.valueOf(dao.codigoIbge(estado1, cidade1));
            codIbgeC2 = String.valueOf(dao.codigoIbge(estado2, cidade2));
            String viewBox = geo.getViewBox(codIbgeC1, codIbgeC2);
                
            pathC1 = geo.getPath(codIbgeC1);
            pathC2 = geo.getPath(codIbgeC2);
            
            request.setAttribute("c1", c1);
            request.setAttribute("c2", c2);
            request.setAttribute("distancia", distancia);
            request.setAttribute("viewBox", viewBox);
            request.setAttribute("path1", pathC1);
            request.setAttribute("path2", pathC2);
            
            RequestDispatcher ds = request.getRequestDispatcher("index.jsp");
            ds.forward(request, response);
            
        } catch (SQLException | ClassNotFoundException | ParseException ex) {
            Logger.getLogger(BuscarCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
