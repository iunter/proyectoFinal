using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using ProyectoFinal.Models;
using Newtonsoft.Json.Linq;
using System.Web;
using Newtonsoft.Json;
using System.Web.Script.Serialization;
using System.Data.OleDb;
using System.Data;
using MySql.Data.MySqlClient;

namespace ApiRest.Models
{
    public class Turno
    {
        public int idTurno { get; set; }
        public DateTime Fecha { get; set; }
        public DateTime Hora { get; set; }
        public int idpaciente { get; set; }
        public int idcentro { get; set; }
        private MySqlConnection conn(MySqlConnection con)
        {
            con.ConnectionString = "Server=localhost;Database=consola;uid=root;password= ;";
            con.Open();
            return con;
        }
        public void NuevoTurno()
        {
            MySqlConnection con;
            con = new MySqlConnection();
            con = conn(con);
            MySqlCommand Comando = new MySqlCommand();
            Comando.Connection = con;
            Comando.CommandType = CommandType.StoredProcedure;
            Comando.CommandText = "consola.NuevoTurno";
            MySqlParameter par = new MySqlParameter("pFecha", Fecha);
            MySqlParameter par2 = new MySqlParameter("pHora", Hora);
            MySqlParameter par3 = new MySqlParameter("pidpaciente", idpaciente);
            MySqlParameter par4 = new MySqlParameter("pidcentro", idcentro);
            Comando.Parameters.Add(par);
            Comando.Parameters.Add(par2);
            Comando.Parameters.Add(par3);
            Comando.Parameters.Add(par4);
            Comando.ExecuteNonQuery();
            con.Close();
        }
    }
}