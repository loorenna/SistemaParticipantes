package org.example.sistemaparticipantes.dao;


import org.example.sistemaparticipantes.Conexion;
import org.example.sistemaparticipantes.model.Participante;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ParticipanteDAO {

    private Connection con;

    public ParticipanteDAO() {

        con = Conexion.getInstancia().getConexion();

    }
    public void guardar(Participante p){

        try{

            String sql = """
        INSERT INTO participantes
        (cedula,nombre,apellido,edad,correo,
        estado_civil,jornada,categoria,observaciones)

        VALUES(?,?,?,?,?,?,?,?,?)
        """;

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1,p.getCedula());
            ps.setString(2,p.getNombre());
            ps.setString(3,p.getApellido());
            ps.setInt(4,p.getEdad());
            ps.setString(5,p.getCorreo());
            ps.setString(6,p.getEstadoCivil());
            ps.setString(7,p.getJornada());
            ps.setString(8,p.getCategoria());
            ps.setString(9,p.getObservaciones());

            ps.executeUpdate();

        }catch(Exception e){

            e.printStackTrace();

        }

    }
    public ArrayList<Participante> listar(){

        ArrayList<Participante> lista = new ArrayList<>();

        try{

            String sql = "SELECT * FROM participantes";

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){

                Participante p = new Participante();

                p.setId(rs.getInt("id"));
                p.setCedula(rs.getString("cedula"));
                p.setNombre(rs.getString("nombre"));
                p.setApellido(rs.getString("apellido"));
                p.setEdad(rs.getInt("edad"));
                p.setCorreo(rs.getString("correo"));
                p.setEstadoCivil(rs.getString("estado_civil"));
                p.setJornada(rs.getString("jornada"));
                p.setCategoria(rs.getString("categoria"));
                p.setObservaciones(rs.getString("observaciones"));

                lista.add(p);

            }

        }catch(Exception e){

            e.printStackTrace();

        }

        return lista;

    }
    public void actualizar(Participante p){

        try{

            String sql="""
        UPDATE participantes
        SET cedula=?,
            nombre=?,
            apellido=?,
            edad=?,
            correo=?,
            estado_civil=?,
            jornada=?,
            categoria=?,
            observaciones=?
        WHERE id=?
        """;

            PreparedStatement ps=con.prepareStatement(sql);

            ps.setString(1,p.getCedula());
            ps.setString(2,p.getNombre());
            ps.setString(3,p.getApellido());
            ps.setInt(4,p.getEdad());
            ps.setString(5,p.getCorreo());
            ps.setString(6,p.getEstadoCivil());
            ps.setString(7,p.getJornada());
            ps.setString(8,p.getCategoria());
            ps.setString(9,p.getObservaciones());
            ps.setInt(10,p.getId());

            ps.executeUpdate();

        }catch(Exception e){

            e.printStackTrace();

        }

    }
    public void eliminar(int id){

        try{

            String sql="DELETE FROM participantes WHERE id=?";

            PreparedStatement ps=con.prepareStatement(sql);

            ps.setInt(1,id);

            ps.executeUpdate();

        }catch(Exception e){

            e.printStackTrace();

        }

    }
    public boolean existeCorreo(String correo){

        try{

            String sql="SELECT * FROM participantes WHERE correo=?";

            PreparedStatement ps=con.prepareStatement(sql);

            ps.setString(1,correo);

            ResultSet rs=ps.executeQuery();

            return rs.next();

        }catch(Exception e){

            e.printStackTrace();

        }

        return false;

    }
    public Participante buscarPorId(int id){

        try{

            String sql="SELECT * FROM participantes WHERE id=?";

            PreparedStatement ps=con.prepareStatement(sql);

            ps.setInt(1,id);

            ResultSet rs=ps.executeQuery();

            if(rs.next()){

                Participante p=new Participante();

                p.setId(rs.getInt("id"));
                p.setCedula(rs.getString("cedula"));
                p.setNombre(rs.getString("nombre"));
                p.setApellido(rs.getString("apellido"));
                p.setEdad(rs.getInt("edad"));
                p.setCorreo(rs.getString("correo"));
                p.setEstadoCivil(rs.getString("estado_civil"));
                p.setJornada(rs.getString("jornada"));
                p.setCategoria(rs.getString("categoria"));
                p.setObservaciones(rs.getString("observaciones"));

                return p;

            }

        }catch(Exception e){

            e.printStackTrace();

        }

        return null;

    }

}

