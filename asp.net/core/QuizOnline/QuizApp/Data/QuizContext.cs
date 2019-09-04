using Microsoft.EntityFrameworkCore;
using QuizOnline.Models;
using System;
using System.Collections.Generic;
using System.Text;

namespace QuizOnline.Data
{
    public class QuizContext : DbContext
    {
        public DbSet<Quiz> Quizzes { get; set; }
        public DbSet<Question> Questions { get; set; }

        public QuizContext(DbContextOptions baseOptions) : base(baseOptions)
        {
        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<Question>().HasOne<Quiz>(q => q.Quiz)
                .WithMany(q => q.Questions)
                .OnDelete(DeleteBehavior.Cascade);
            modelBuilder.Entity<Quiz>().ToTable("Quizzes");
            modelBuilder.Entity<Question>().ToTable("Questions");
            base.OnModelCreating(modelBuilder);
        }
    }
}
