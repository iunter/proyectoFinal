using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
//using Microsoft.Jet.OleDB;
using System.Data.OleDb;
using System.Data;

namespace ProyectoFinal.Models
{
    public class Evento
    {
        public int Id {get;set;}
        //como es un poco + complejo con otras clases, lo comento
        //public Materia Materia { get; set; }
        //public TipoEvento Tipo { get; set; }
        public int Materia { get; set; }
        public int Tipo { get; set; }
        public DateTime Fecha { get; set; }
        public string Descripcion { get; set; }
            string dire = @"Provider=Microsoft.Jet.OLEDB.4.0; Data Source=Z:\Proyecto\ApiRest\BaseDeDatos.mdb";
        public List<Evento> ObtenerEventos()
        {
            OleDbConnection Con = new OleDbConnection();
            Con.ConnectionString = dire;
            Con.Open();
            OleDbCommand Consulta = Con.CreateCommand();
            Consulta.CommandType = CommandType.StoredProcedure;
            Consulta.CommandText = "TraerEvento";
            OleDbDataReader drEventos = Consulta.ExecuteReader();
            List<Evento> ListaEventos = new List<Evento>();
            while (drEventos.Read()){
                Evento MiEvento = new Evento();
                MiEvento.Id = Convert.ToInt32(drEventos["Id"]);
                MiEvento.Materia = Convert.ToInt32(drEventos["IdMateria"]);
                MiEvento.Tipo = Convert.ToInt32(drEventos["IdTipo"]);
                MiEvento.Fecha = Convert.ToDateTime(drEventos["Fecha"]);
                MiEvento.Descripcion = drEventos["Descripcion"].ToString();
                ListaEventos.Add(MiEvento);
            }
            return ListaEventos;
        }
        public void GuardarEvento(Evento evento)
        {
            OleDbConnection Con = new OleDbConnection();
            Con.ConnectionString = dire;
            Con.Open();
            OleDbCommand Consulta = Con.CreateCommand();
            Consulta.CommandType = CommandType.StoredProcedure;
            Consulta.CommandText = "CrearEvento";
            //parametros
            OleDbParameter pTipo = new OleDbParameter("pIdTipo", evento.Tipo);
            OleDbParameter pMateria = new OleDbParameter("pIdMateria", evento.Materia);
            OleDbParameter pFecha = new OleDbParameter("pFecha", evento.Fecha);
            OleDbParameter pDescripcion = new OleDbParameter("pDescripcion", evento.Descripcion);
            Consulta.Parameters.Add(pTipo);
            Consulta.Parameters.Add(pMateria);
            Consulta.Parameters.Add(pFecha);
            Consulta.Parameters.Add(pDescripcion);
            Consulta.ExecuteNonQuery();
        }
        public void BorrarEvento(int id)
        {
            OleDbConnection Con = new OleDbConnection();
            Con.ConnectionString = dire;
            Con.Open();
            OleDbCommand Consulta = Con.CreateCommand();
            Consulta.CommandType = CommandType.StoredProcedure;
            Consulta.CommandText = "BorrarEvento";
            //parametros
            OleDbParameter pId = new OleDbParameter("pIdTipo", id);
            Consulta.Parameters.Add(pId);
            Consulta.ExecuteNonQuery();
        }
        public void ModificarEvento(Evento evento)
        {
            OleDbConnection Con = new OleDbConnection();
            Con.ConnectionString = dire;
            Con.Open();
            OleDbCommand Consulta = Con.CreateCommand();
            Consulta.CommandType = CommandType.StoredProcedure;
            Consulta.CommandText = "ModificarEvento";
            //parametros
            OleDbParameter pTipo = new OleDbParameter("pIdTipo", evento.Tipo);
            OleDbParameter pMateria = new OleDbParameter("pIdMateria", evento.Materia);
            OleDbParameter pFecha = new OleDbParameter("pFecha", evento.Fecha);
            OleDbParameter pDescripcion = new OleDbParameter("pDescripcion", evento.Descripcion);
            Consulta.Parameters.Add(pTipo);
            Consulta.Parameters.Add(pMateria);
            Consulta.Parameters.Add(pFecha);
            Consulta.Parameters.Add(pDescripcion);
            Consulta.ExecuteNonQuery();
        }
        public Evento BuscarUno (int id)
        {
            OleDbConnection Con = new OleDbConnection();
            Con.ConnectionString = dire;
            Con.Open();
            OleDbCommand Consulta = Con.CreateCommand();
            Consulta.CommandType = CommandType.StoredProcedure;
            Consulta.CommandText = "VerEvento";
            //parametros
            OleDbParameter pId = new OleDbParameter("pIdTipo", id);
            Consulta.Parameters.Add(pId);
            OleDbDataReader drEventos = Consulta.ExecuteReader();
            Evento MiEvento = new Evento();
            while (drEventos.Read())
            {
                MiEvento.Id = Convert.ToInt32(drEventos["Id"]);
                MiEvento.Materia = Convert.ToInt32(drEventos["IdMateria"]);
                MiEvento.Tipo = Convert.ToInt32(drEventos["IdTipo"]);
                MiEvento.Fecha = Convert.ToDateTime(drEventos["Fecha"]);
                MiEvento.Descripcion = drEventos["Descripcion"].ToString();
            }
            return MiEvento;
        }
    }
}