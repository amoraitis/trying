using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.EntityFrameworkCore;
using Newtonsoft.Json;
using QuizOnline.App.Abstractions;
using QuizOnline.App.Models;
using QuizOnline.Models;
using QuizOnline.Data;

namespace QuizOnline.App.Pages
{
    public class IndexModel : PageModel
    {
        public IEnumerable<Quiz> Quizzes { get; set; }
        private QuizContext db;

        public IndexModel(QuizContext context)
        {
            db = context;
        }

        public async Task OnGet()
        {
            Quizzes = await db.Quizzes.ToListAsync();
            ViewData["Title"] = "Home";
        }       
    }
}
