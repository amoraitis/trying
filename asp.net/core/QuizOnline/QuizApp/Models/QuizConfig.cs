using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.EntityFrameworkCore;
using Newtonsoft.Json;
using QuizOnline.Models;
using QuizOnline.Data;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Threading.Tasks;

namespace QuizOnline.App.Models
{
    public static class QuizConfig
    {
        public async static Task UseSampleQuestionsAsync(QuizContext context, string webRootPath)
        {
            if (context.Quizzes.Any())
                return;
            // load a sample JSON file of questions
            string json = File.ReadAllText(Path.Combine(webRootPath, "sample_questions.json"));

            List<Quiz> quizzes = JsonConvert.DeserializeObject<List<Quiz>>(json);
            
            foreach (Quiz quiz in quizzes)
            {
                await context.Quizzes.AddAsync(quiz);
            }
            // Save the entities to the data store
            context.SaveChanges();
        }
    }
}