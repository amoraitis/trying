using QuizOnline.Models;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

namespace QuizOnline.App.Abstractions
{
    public interface ISessionHelper
    {
        Tuple<Quiz, Dictionary<int,string>> GetContent();
        void SetContent(Tuple<Quiz, Dictionary<int, string>> content);
    }
}
