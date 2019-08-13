using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.Extensions.Caching.Distributed;
using Newtonsoft.Json;
using QuizOnline.App.Abstractions;
using QuizOnline.App.ViewModels.Home;
using QuizOnline.Models;

namespace QuizOnline.App.Pages
{
    public class FinishModel : PageModel
    {
        private readonly ISessionHelper sessionHelper;

        public FinishViewModel FinishViewModel { get; set; }


        public FinishModel(ISessionHelper sessionHelper)
        {
            this.sessionHelper = sessionHelper;
        }

        public void OnGet()
        {
            var content = sessionHelper.GetContent();
            
            FinishViewModel = new FinishViewModel
            {
                Quiz = content.Item1,
                Answers = content.Item2
            };
            FinishViewModel.CorrectAnswers = FinishViewModel.Quiz.Questions.ToList().Count(q =>
                q.CorrectAnswer.Equals(FinishViewModel.Answers[q.QuestionId-1]));
            ViewData["Title"] = "End of Quiz";
        }
    }
}