using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using ProyectoFinal.Models;
using Newtonsoft.Json.Linq;
using System.Web;
using Newtonsoft.Json;
using System.Web.Script.Serialization;
namespace ApiRest.Controllers
{
    public class PacienteController : ApiController
    {
        // GET api/paciente
        public IEnumerable<string> Get()
        {
            Paciente pac = new Paciente();
            pac.Nombre = "roberrrto";
            pac.Apellido = "otrrrebor";
            pac.dni = 2;
            pac.foto = "ieee";
            pac.obrasocial = "PAMI";
            pac.socio = 666;
            pac.lat = 1;
            pac.longg = 2;
            pac.AltaPaciente();
            return new string[] { "value1", "value2" };
        }

        // GET api/paciente/5
        public void GetOne()
        {
            Paciente pac = new Paciente();
            pac.Nombre = "roberrrto";
            pac.Apellido = "otrrrebor";
            pac.dni = 2;
            pac.foto = "ieee";
            pac.obrasocial = "PAMI";
            pac.socio = 666;
            pac.lat = 1;
            pac.longg = 2;
            pac.AltaPaciente();
        }

        // POST api/paciente
        public void Post(JObject objeto)
        {
            Paciente pac = new Paciente();
            pac = JsonConvert.DeserializeObject<Paciente>(objeto.ToString());
            pac.AltaPaciente();
            if(objeto == null)
            {
                throw new HttpResponseException(new HttpResponseMessage(HttpStatusCode.NotAcceptable));
            }
        }

        // PUT api/paciente/5
        public void Put()
        {
            
        }

        // DELETE api/paciente/5
        public void Delete(int id)
        {
        }
    }
}
