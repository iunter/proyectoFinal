using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using ApiRest.Models;
using Newtonsoft.Json.Linq;
using System.Web;
using Newtonsoft.Json;
using System.Web.Script.Serialization;

namespace ApiRest.Controllers
{
    public class TurnoController : ApiController
    {
        Turno miTurno = new Turno();
        // GET api/turno
        public IEnumerable<string> Get()
        {
            DateTime date = new DateTime();
            date = DateTime.Now;
            miTurno.Hora = date;
            date = DateTime.Today;
            miTurno.Fecha = date;
            miTurno.idcentro = 1;
            miTurno.idpaciente = 1;
            miTurno.NuevoTurno();
            return new string[] { "value1", "value2" };
        }

        // GET api/turno/5
        public string Get(int id)
        {
            return "value";
        }

        // POST api/turno
        public void Post(JObject turno)
        {
            miTurno = JsonConvert.DeserializeObject<Turno>(turno.ToString());
            miTurno.NuevoTurno();
            if (turno == null)
            {
                throw new HttpResponseException(new HttpResponseMessage(HttpStatusCode.NotAcceptable));
            }
        }

        // PUT api/turno/5
        public void Put(int id, [FromBody]string value)
        {
        }

        // DELETE api/turno/5
        public void Delete(int id)
        {
        }
    }
}
