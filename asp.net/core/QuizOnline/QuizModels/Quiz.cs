using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Threading.Tasks;
using static System.Net.Mime.MediaTypeNames;

namespace QuizOnline.Models
{
    public class Quiz
    {
        [DatabaseGenerated(DatabaseGeneratedOption.None), Key]
        public int QuizId { get; set; }
        public string Title { get; set; }
        public string Description { get; set; }
        public ICollection<Question> Questions { get; set; }

        public Quiz()
        {
            Questions = new HashSet<Question>();
        }
    }
}