using QuizOnline.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace QuizOnline.App.ViewModels.Home
{
    public class QuestionViewModel
    {
        public Question Question { get; set; }
        public string Answer { get; set; }
        public int Number { get; set; }
        public int Total { get; set; }
    }
}
