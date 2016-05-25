using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using ProyectoFinal.Models;

namespace ProyectoFinal.Servicios
{
    public class RepositorioEvento
    {
        private const string CacheKey = "EventoStore";
         /*public RepositorioEvento()
        {
            var ctx = HttpContext.Current;

            if (ctx != null)
            {
                if (ctx.Cache[CacheKey] == null)
                {
                    var contacts = new Evento[]
            {
                new Evento
                {
                    Id = 1, Nombre = "Glenn Block", Materia=4
                },
                new Evento
                {
                    Id = 2, Nombre = "Dan Roth", Materia=3
                }
            };

                    ctx.Cache[CacheKey] = contacts;
                }
            }
        }
        
        //muestra eventos, todavia no lo necesito
        public Evento[] ObtenerEventos()
        {
            var ctx = HttpContext.Current;
            if (ctx != null)
            {
                return (Evento[])ctx.Cache[CacheKey];
            }
            return new Evento[]
                {
                    //aca se obitnenen eventos

                    new Evento
                    {
                        Id=0,
                        Nombre="PlaceHolder"
                    }
                };
        }
        public bool GuardarEvento(Evento evento)
        {
            //guarda contacto, responde bien o mal si pudo o no 
            //es lo que tengo que hacer yo
            var ctx = HttpContext.Current;
            if (ctx != null)
            {
                try
                {
                    var currentData = ((Evento[])ctx.Cache[CacheKey]).ToList();
                    currentData.Add(evento);
                    ctx.Cache[CacheKey] = currentData.ToArray();
                    return true;
                }
                catch (Exception ex)
                {
                    Console.WriteLine(ex.ToString());
                    return false;
                }
            }
            return false;
        }*/
    }
}