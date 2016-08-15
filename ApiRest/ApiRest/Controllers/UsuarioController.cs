using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using ProyectoFinal.Servicios;
using ProyectoFinal.Models;
using Newtonsoft.Json.Linq;
using System.Web;
using Newtonsoft.Json;
using System.Web.Script.Serialization;

//como es el using del json? porque me aparece error
namespace ProyectoFinal.Controllers
{
    public class UsuarioController : ApiController
    {
        
        private Usuario miUsuario = new Usuario();
        private RepositorioEvento repositorioEvento;
        public UsuarioController()
        {            
            this.repositorioEvento = new RepositorioEvento();
        }
        public List<Usuario> Get()
        {
            //return repositorioEvento.ObtenerEventos();
            //var json = new JavaScriptSerializer().Serialize(miUsuario);
            //JsonConvert.SerializeObject(json);
            List<Usuario> usuarios = miUsuario.TraerUsuarios();
            return usuarios;
        }
        //Nuevo evento:

        public void Post(JObject Objeto)
        {
            miUsuario = JsonConvert.DeserializeObject<Usuario>(Objeto.ToString());
            miUsuario.AltaUsuario();
            if(Objeto == null)
            {
                throw new HttpResponseException(new HttpResponseMessage(HttpStatusCode.NotAcceptable));
            }
            /*miUsuario.Nombre = "a";
            miUsuario.Apellido = "b";
            miUsuario.dni = 1;
            miUsuario.Foto = "c";
            miUsuario.Matricula = "d";
            miUsuario.idclave = 1;
            miUsuario.idpaciente = 1;
            miUsuario.idpublicacion = 1;
            miUsuario.idtipo = 1;
            miUsuario.AltaUsuario();
                   
            if (usu == null)
            {
                throw new HttpResponseException(new HttpResponseMessage(HttpStatusCode.NotAcceptable));
            }*/
        }
        public void GetOne()
        {
           
        }
        [HttpPost]
        public void Modify(JObject evento)
        {
            
            if (evento == null)
            {
                throw new HttpResponseException(new HttpResponseMessage(HttpStatusCode.NotAcceptable));
            }
     
        }
        public void Delete(int id)
        {
            
        }
    }
}
