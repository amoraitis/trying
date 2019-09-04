using System.Collections.Generic;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.EntityFrameworkCore;
using QuizOnline.Models;
using QuizOnline.Data;
using System;
using Newtonsoft.Json;
using Microsoft.Extensions.Caching.Distributed;
using Microsoft.AspNetCore.Mvc;
using System.Threading.Tasks;
using QuizOnline.App.Abstractions;

namespace QuizOnline.App.Pages
{
    public class TakeQuizModel : PageModel
    {
        
        public Quiz Quiz { get; set; }
        private QuizContext db;
        private readonly ISessionHelper sessionHelper;

        public TakeQuizModel(QuizContext context, ISessionHelper sessionHelper)
        {
            db = context;
            this.sessionHelper = sessionHelper;
        }

        public async Task OnGetAsync(int id)
        {           
            Quiz = await db.Quizzes
                .Include(q => q.Questions)
                .AsNoTracking().SingleOrDefaultAsync(q=>q.QuizId == id);
            if (Quiz == null)
            {
                RedirectToPage(NotFound($"A quiz with the ID of {id} was not found."));
                return;
            }
            ViewData["Title"] = $"{Quiz.Title} Quiz";
            var answers = new Dictionary<int, string>();
            foreach (Question question in Quiz.Questions)
            {
                answers.Add(question.QuestionId, string.Empty);
            }
            var data = new Tuple<Quiz, Dictionary<int, string>>(Quiz, answers);
            sessionHelper.SetContent(data);
            
            
            
        }
    }
}
