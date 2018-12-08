/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.edu.dao;

import com.ifpb.edu.conexao.ConexaoBrasilDb;
import com.ifpb.edu.model.Cidade;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.io.ParseException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Matheus
 */
public class GeometriaCidade {
    
    private CidadeDao dao;
    private ConexaoBrasilDb connect;
    private PreparedStatement stmt;
    private ResultSet rs;
    
    public GeometriaCidade(){
        this.dao = new CidadeDao();
        this.connect = new ConexaoBrasilDb();
        this.stmt = null;
        this.rs = null;
    }
    
    public double getDistancia(Cidade c1, Cidade c2) throws SQLException, ClassNotFoundException, ParseException{
        
        int codigoC1 = dao.codigoIbge(c1.getEstado(), c1.getNome());
        int codigoC2 = dao.codigoIbge(c2.getEstado(), c2.getNome());
        Geometry g1 = dao.getGeoCidade(c1.getNome(), codigoC1);
        Geometry g2 = dao.getGeoCidade(c2.getNome(), codigoC2);
        
        double distancia = calculaDistancia(g1, g2);
        
        return distancia;
    }

    private double calculaDistancia(Geometry g1, Geometry g2) {
        return g1.getCentroid().distance(g2.getCentroid()) * (40075/360);
    }

    public String getViewBox(String codigo1, String codigo2) throws SQLException {
        
        String viewBox = null;
        Connection con = connect.getConnection();
        
        String sql = "SELECT getViewBox(?, ?)";
        
        stmt = con.prepareStatement(sql);
        stmt.setString(1, codigo1);
        stmt.setString(2, codigo2);
        
        rs = stmt.executeQuery();
        if(rs.next()){
            viewBox = rs.getString(1);
        }
        
        rs.close();
        stmt.close();
        con.close();
        
        
        return viewBox;
        
    }
    
    public String getPath(String codIbge) throws SQLException {
        
        Connection con = connect.getConnection();
        
        String sql = "SELECT ST_AsSVG(geom) "
                + "FROM municipio "
                + "WHERE cd_geocmu ilike ?";
        stmt = con.prepareStatement(sql);
        stmt.setString(1, codIbge);
        
        rs = stmt.executeQuery();
        
        String path = null;
        if(rs.next()){
            return path = rs.getString(1);
        }
        return null;
    }
}
