using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using MySql.Data.MySqlClient;
using System.Data;
namespace ProyectoFinal.Models
{
    public class Paciente
    {
        public int Id { get; set; }
        public string Nombre { get; set; }
        public string Apellido { get; set; }
        public int dni { get; set; }
        public string obrasocial { get; set; }
        public int socio { get; set; }
        public string foto { get; set; }
        public int lat { get; set; }
        public int longg { get; set; }
        private MySqlConnection conn(MySqlConnection con)
        {
            con.ConnectionString = "Server=localhost;Database=consola;uid=root;password= ;";
            con.Open();
            return con;
        }
        public void AltaPaciente()
        {
            MySqlConnection con;
            con = new MySqlConnection();
            con = conn(con);
            MySqlCommand Comando = new MySqlCommand();
            Comando.Connection = con;
            Comando.CommandType = CommandType.StoredProcedure;
            Comando.CommandText = "consola.AltaPaciente";
            MySqlParameter par = new MySqlParameter("pnombre", Nombre);
            MySqlParameter par2 = new MySqlParameter("papellido", Apellido);
            MySqlParameter par3 = new MySqlParameter("pdni", dni);           
            MySqlParameter par4 = new MySqlParameter("pobrasocial", obrasocial);
            MySqlParameter par5 = new MySqlParameter("psocio", socio);
            MySqlParameter par6 = new MySqlParameter("pfoto", foto);
            MySqlParameter par7 = new MySqlParameter("plat", lat);
            MySqlParameter par8 = new MySqlParameter("plong", longg);
            Comando.Parameters.Add(par);
            Comando.Parameters.Add(par2);
            Comando.Parameters.Add(par3);
            Comando.Parameters.Add(par4);
            Comando.Parameters.Add(par5);
            Comando.Parameters.Add(par6);
            Comando.Parameters.Add(par7);
            Comando.Parameters.Add(par8);
            Comando.ExecuteNonQuery();
            con.Close();
        }
    }
}