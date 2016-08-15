using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
//using Microsoft.Jet.OleDB;
using System.Data.OleDb;
using System.Data;
using MySql.Data.MySqlClient;

namespace ProyectoFinal.Models
{
    public class Usuario
    {
        public int Id { get; set; }
        public string Nombre { get; set; }
        public string Apellido { get; set; }
        public int dni { get; set; }
        public string Foto { get; set; }
        public string Matricula { get; set; }
        public int idpaciente { get; set; }
        public string paciente { get; set; }
        public int idtipo { get; set; } 
        public string tipo { get; set; }
        public int idclave { get; set; }
        public string clave { get; set; }
        public int idpublicacion { get; set; }
        private MySqlConnection conn(MySqlConnection con)
        {
            con.ConnectionString = "Server=localhost;Database=consola;uid=root;password= ;";
            con.Open();
            return con;
        }
        public void AltaUsuario()
        {
            MySqlConnection con;
            con = new MySqlConnection();
            con = conn(con);
            MySqlCommand Comando = new MySqlCommand();
            Comando.Connection = con;
            Comando.CommandType = CommandType.StoredProcedure;
            Comando.CommandText = "consola.AltaUsuario";
            MySqlParameter par = new MySqlParameter("pnombre", Nombre);
            MySqlParameter par2 = new MySqlParameter("papellido", Apellido);
            MySqlParameter par3 = new MySqlParameter("pdni", dni);
            MySqlParameter par4 = new MySqlParameter("pfoto", Foto);
            MySqlParameter par5 = new MySqlParameter("pmatricula", Matricula);
            MySqlParameter par6 = new MySqlParameter("pidpaciente", idpaciente);
            MySqlParameter par7 = new MySqlParameter("pidtipo", idtipo);
            MySqlParameter par8 = new MySqlParameter("pidclave", idclave);
            MySqlParameter par9 = new MySqlParameter("pidpublicacion", idpublicacion);
            Comando.Parameters.Add(par);
            Comando.Parameters.Add(par2);
            Comando.Parameters.Add(par3);
            Comando.Parameters.Add(par4);
            Comando.Parameters.Add(par5);
            Comando.Parameters.Add(par6);
            Comando.Parameters.Add(par7);
            Comando.Parameters.Add(par8);
            Comando.Parameters.Add(par9);
            Comando.ExecuteNonQuery();
            con.Close();
        }
        public List<Usuario> TraerUsuarios()
        {
            List<Usuario> dev= new List<Usuario>();
            MySqlConnection con;
            con = new MySqlConnection();
            con = conn(con);
            MySqlCommand Comando = new MySqlCommand();
            Comando.Connection = con;
            Comando.CommandType = CommandType.StoredProcedure;
            Comando.CommandText = "consola.TraerUsuarios";
            MySqlDataReader dr = Comando.ExecuteReader();
            Usuario miUsuario;
            while(dr.Read())
            {
                miUsuario = new Usuario();
                miUsuario.Id = dr.GetInt32("idusuario");
                miUsuario.Nombre = dr.GetString("nombre");
                miUsuario.Apellido = dr.GetString("apellido");
                miUsuario.dni = dr.GetInt32("dni");
                miUsuario.Foto = dr.GetString("foto");
                miUsuario.Matricula = dr.GetString("matricula");
                miUsuario.idpaciente = dr.GetInt32("idpaciente");
                miUsuario.idtipo = dr.GetInt32("idtipo");
                miUsuario.idclave = dr.GetInt32("idclave");
                miUsuario.idpublicacion = dr.GetInt32("idpublicacion");
                dev.Add(miUsuario);
            }
            con.Close();
            return dev;
        }
        
        
    }
}