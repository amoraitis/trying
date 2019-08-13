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
    public class QuestionModel : PageModel
    {
        private readonly ISessionHelper sessionHelper;

        public QuestionViewModel QuestionViewModel { get; set; }

        public QuestionModel(ISessionHelper sessionHelper)
        {
            this.sessionHelper = sessionHelper;
        }

        public void OnGet(int id)
        {
            var content = sessionHelper.GetContent();
            var quiz = content.Item1;
            var answers = content.Item2;
            QuestionViewModel = new QuestionViewModel
            {
                Question = quiz.Questions.Where(q => q.Id == id).FirstOrDefault(),
                Answer = answers.ContainsKey(id) ?
                    answers[id] : string.Empty,
                Number = id,
                Total = quiz.Questions.Count()
            };
            ViewData["Title"] = $"Question {QuestionViewModel.Number} of {QuestionViewModel.Total}";
        }

        public IActionResult OnPostAsync(int id, string submit, string answer)
        {
            var content = sessionHelper.GetContent();
            var answers = content.Item2;
            answers[id - 1] = answer;
            content = new Tuple<Quiz, Dictionary<int, string>>(content.Item1, answers);
            sessionHelper.SetContent(content);
           
            if (submit == "Previous")
            {
                id--;
            }
            else if (submit == "Next")
            {
                id++;
            }
            else if (submit == "Finish")
            {
                return RedirectToPage("/Finish");
            }
            else
            {
                return RedirectToPage("/Index");
            }
            return RedirectToPage("/Question", new { id = id });
        }
    }
}
