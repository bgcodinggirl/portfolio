using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Calculator
{
    public partial class Calculator : Form
    {
        double result=0;
        string sign="";
        bool flag = false;
        Stack<string> history = new Stack<string>();
        string historyText;
        double memory;
        public Calculator()
        {
            InitializeComponent();
        }

        private void numberButton_Click(object sender, EventArgs e)
        {
            if ((calculation.Text == "0") || (flag)) calculation.Clear();
            
                Button b = (Button)sender;
                calculation.Text = calculation.Text + b.Text;
                historyText += b.Text;
            
        }

        private void operator_Click(object sender, EventArgs e)
        {
            Button b = (Button)sender;
            if (calculation.Text.Contains('%')!=true)
            {
                btnResult.PerformClick();
                result = Double.Parse(calculation.Text);
                sign = b.Text;
                flag = true;
                historyText += b.Text;
            }
            else
            {
                btnResult.PerformClick();
                result = Double.Parse(calculation.Text)/100.0;
                sign = b.Text;
                flag = true;
                historyText += b.Text;
            }

        }
        
        private void btnResult_Click(object sender, EventArgs e)
        {
            if (calculation.Text.Contains('%'))
            {
                switch (sign)
                {
                    case "+": calculation.Text = ((result + Convert.ToDouble(calculation.Text))/100.0).ToString(); break;
                    case "-": calculation.Text = ((result - Convert.ToDouble(calculation.Text)) / 100.0).ToString(); break;
                    case "*": calculation.Text = ((result * Convert.ToDouble(calculation.Text)) / 100.0).ToString(); break;
                    case "/": calculation.Text = ((result / Convert.ToDouble(calculation.Text)) / 100.0).ToString(); break;
                    default: break;
                }
            }
            else
            {
                switch (sign)
                {
                    case "+": calculation.Text = (result + Convert.ToDouble(calculation.Text)).ToString(); break;
                    case "-": calculation.Text = (result - Convert.ToDouble(calculation.Text)).ToString(); break;
                    case "*": calculation.Text = (result * Convert.ToDouble(calculation.Text)).ToString(); break;
                    case "/": calculation.Text = (result / Convert.ToDouble(calculation.Text)).ToString(); break;
                    default: break;
                }
            }
            flag = false;
            memory=Convert.ToDouble(calculation.Text);
            
        }

        private void btnClear_Click(object sender, EventArgs e)
        {
            flag = false;
            calculation.Text = "0";
            result = 0;
            sign = "";
            history.Clear();
            operationsDone.Text = "";
            historyText = "";
        }

        private void btnMR_Click(object sender, EventArgs e)
        {
            MR.Text = "Memory: " + memory;
        }

        private void btnMC_Click(object sender, EventArgs e)
        {
            memory=0;
            MR.Text = "Memory: 0";
        }

        private void btnMPlus_Click(object sender, EventArgs e)
        {
            calculation.Text = (Double.Parse(calculation.Text) + memory).ToString();
        }

        private void btnMMinus_Click(object sender, EventArgs e)
        {

            calculation.Text = (Double.Parse(calculation.Text) - memory).ToString();
        }

        private void button11_Click(object sender, EventArgs e)
        {
            historyText += "=" + memory;
            history.Push(historyText);
            operationsDone.Text=history.Pop();
        }
        
    }
}
