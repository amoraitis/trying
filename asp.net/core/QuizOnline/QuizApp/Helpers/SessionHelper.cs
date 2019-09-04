using Microsoft.AspNetCore.Http;
using Newtonsoft.Json;
using QuizOnline.App.Abstractions;
using QuizOnline.Models;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

namespace QuizOnline.App.Helpers
{
    public class SessionHelper : ISessionHelper
    {
        
        public static JsonSerializerSettings settings = new JsonSerializerSettings
        {
            PreserveReferencesHandling = PreserveReferencesHandling.All
        };
        private readonly IHttpContextAccessor httpContextAccessor;

        public SessionHelper(IHttpContextAccessor httpContextAccessor)
        {
            this.httpContextAccessor = httpContextAccessor;
        }

        public Tuple<Quiz, Dictionary<int, string>> GetContent()
        {
            bool gotValue = httpContextAccessor.HttpContext
                .Request.Cookies.TryGetValue("quiz", out string content);

            if (gotValue)
                return JsonConvert.DeserializeObject<Tuple<Quiz, Dictionary<int, string>>>(content);
            return null;

        }

        public void SetContent(Tuple<Quiz, Dictionary<int, string>> content)
        {
            
            var quizJSON = JsonConvert.SerializeObject(content, Formatting.None, settings);
            if (httpContextAccessor.HttpContext.Request.Cookies.ContainsKey("quiz"))
                httpContextAccessor.HttpContext.Response.Cookies.Append("quiz", quizJSON);
            else
                httpContextAccessor.HttpContext.Response.Cookies.Append("quiz", quizJSON,
                new CookieOptions {
                    Expires = DateTime.Now.AddMinutes(10),
                    IsEssential = true,
                    Secure = true
                }
            );
        }
    }
}
