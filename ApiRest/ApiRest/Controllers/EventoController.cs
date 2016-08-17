using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using ProyectoFinal.Servicios;
using ProyectoFinal.Models;
using Newtonsoft.Json.Linq;
//using System.Web.Script.Serialization;
using Newtonsoft.Json;
//como es el using del json? porque me aparece error
namespace ProyectoFinal.Controllers
{
    public class EventoController : ApiController
    {
        
        private Evento MiEvento = new Evento();
        private RepositorioEvento repositorioEvento;
        public EventoController()
        {            
            this.repositorioEvento = new RepositorioEvento();
        }
        public List<Evento> Get()
        {
            List<Evento> Lista = new List<Evento>();
            Lista = MiEvento.ObtenerEventos();
            return Lista;
            //return repositorioEvento.ObtenerEventos();
        }
        //Nuevo evento:
        [HttpPost]
        public void Post(JObject evento)
        {
            MiEvento = JsonConvert.DeserializeObject<Evento>(evento.ToString());
            MiEvento.GuardarEvento(MiEvento);
            if (evento == null)
            {
                throw new HttpResponseException(new HttpResponseMessage(HttpStatusCode.NotAcceptable));
            }
        }
        public Evento GetOne(int id)
        {
            MiEvento = MiEvento.BuscarUno(id);
            return MiEvento;
        }
        [HttpPost]
        public Evento Modify(JObject evento)
        {
            MiEvento = JsonConvert.DeserializeObject<Evento>(evento.ToString());
            MiEvento.ModificarEvento(MiEvento);
            if (evento == null)
            {
                throw new HttpResponseException(new HttpResponseMessage(HttpStatusCode.NotAcceptable));
            }
            return MiEvento;
        }
        public void Delete(int id)
        {
            MiEvento.BorrarEvento(id);
        }
    }
}
