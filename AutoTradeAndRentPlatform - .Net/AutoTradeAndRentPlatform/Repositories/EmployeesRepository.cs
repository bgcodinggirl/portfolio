using AutoTradeAndRentPlatform.Entities;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Web;

namespace AutoTradeAndRentPlatform.Repositories
{
    public class EmployeesRepository
    {
        public Employee GetById(int id)
        {
            Employee item = null;

            SqlConnection conn = new SqlConnection();
            conn.ConnectionString = @"Server=DESKTOP-DK0K62G\SQLEXPRESS;Database=AutoTrade;User Id=sa;Password=010698;";

            SqlCommand cmd = new SqlCommand();
            cmd.Connection = conn;
            cmd.CommandText = @"
SELECT
  Id,
  Username,
  Password,
  FirstName,
  LastName,
  Phone,
  Email
FROM
  EMPLOYEES
WHERE
  Id = @Id
";

            cmd.Parameters.AddWithValue("@Id", id);

            SqlDataReader reader = null;
            try
            {
                conn.Open();
                reader = cmd.ExecuteReader();
                if (reader.Read())
                {
                    item = new Employee();
                    item.Id = Convert.ToInt32(reader["Id"]);
                    item.Username = Convert.ToString(reader["Username"]);
                    item.Password = Convert.ToString(reader["Password"]);
                    item.FirstName = Convert.ToString(reader["FirstName"]);
                    item.LastName = Convert.ToString(reader["LastName"]);
                    item.Phone = Convert.ToString(reader["Phone"]);
                    item.Email = Convert.ToString(reader["Email"]);

                }
            }
            finally
            {
                reader.Close();
                conn.Close();
            }

            return item;
        }

        public List<Employee> GetAll()
        {
            List<Employee> items = new List<Employee>();

            SqlConnection conn = new SqlConnection();
            conn.ConnectionString = @"Server=DESKTOP-DK0K62G\SQLEXPRESS;Database=AutoTrade;User Id=sa;Password=010698;";

            SqlCommand cmd = new SqlCommand();
            cmd.Connection = conn;
            cmd.CommandText = @"
SELECT
  Id,
  Username,
  Password,
  FirstName,
  LastName,
  Phone,
  Email
FROM
  EMPLOYEES
";

            SqlDataReader reader = null;
            try
            {
                conn.Open();
                reader = cmd.ExecuteReader();
                while (reader.Read())
                {
                    Employee item = new Employee();
                    item.Id = Convert.ToInt32(reader["Id"]);
                    item.Username = Convert.ToString(reader["Username"]);
                    item.Password = Convert.ToString(reader["Password"]);
                    item.FirstName = Convert.ToString(reader["FirstName"]);
                    item.LastName = Convert.ToString(reader["LastName"]);
                    item.Phone = Convert.ToString(reader["Phone"]);
                    item.Email = Convert.ToString(reader["Email"]);
                    items.Add(item);
                }
            }
            finally
            {
                reader.Close();
                conn.Close();
            }

            return items;
        }

        public void Insert(Employee item)
        {
            SqlConnection conn = new SqlConnection();
            conn.ConnectionString = @"Server=DESKTOP-DK0K62G\SQLEXPRESS;Database=AutoTrade;User Id=sa;Password=010698;";

            SqlCommand cmd = new SqlCommand();
            cmd.Connection = conn;
            cmd.CommandText = @"
INSERT INTO EMPLOYEES(
  Username,
  Password,
  FirstName,
  LastName,
  Phone,
  Email
)
VALUES (
  @Username,
  @Password,
  @FirstName,
  @LastName,
  @Phone,
  @Email
)";

            cmd.Parameters.AddWithValue("@Username", item.Username);
            cmd.Parameters.AddWithValue("@Password", item.Password);
            cmd.Parameters.AddWithValue("@FirstName", item.FirstName);
            cmd.Parameters.AddWithValue("@LastName", item.LastName);
            cmd.Parameters.AddWithValue("@Phone", item.Phone);
            cmd.Parameters.AddWithValue("@Email", item.Email);
            try
            {
                conn.Open();
                cmd.ExecuteNonQuery();
            }
            finally
            {
                conn.Close();
            }
        }

        public void Update(Employee item)
        {
            SqlConnection conn = new SqlConnection();
            conn.ConnectionString = @"Server=DESKTOP-DK0K62G\SQLEXPRESS;Database=AutoTrade;User Id=sa;Password=010698;";

            SqlCommand cmd = new SqlCommand();
            cmd.Connection = conn;
            cmd.CommandText = @"
UPDATE EMPLOYEES SET
  Username = @Username,
  Password = @Password,
  FirstName = @FirstName,
  LastName = @LastName,
  Phone = @Phone,
  Email = @Email
WHERE
  Id = @Id";

            cmd.Parameters.AddWithValue("@Username", item.Username);
            cmd.Parameters.AddWithValue("@Password", item.Password);
            cmd.Parameters.AddWithValue("@FirstName", item.FirstName);
            cmd.Parameters.AddWithValue("@LastName", item.LastName);
            cmd.Parameters.AddWithValue("@Phone", item.Phone);
            cmd.Parameters.AddWithValue("@Email", item.Email);
            cmd.Parameters.AddWithValue("@Id", item.Id);

            try
            {
                conn.Open();
                cmd.ExecuteNonQuery();
            }
            finally
            {
                conn.Close();
            }
        }

        public void Delete(int id)
        {
            SqlConnection conn = new SqlConnection();
            conn.ConnectionString = @"Server=DESKTOP-DK0K62G\SQLEXPRESS;Database=AutoTrade;User Id=sa;Password=010698;";

            SqlCommand cmd = new SqlCommand();
            cmd.Connection = conn;
            cmd.CommandText = @"
DELETE FROM EMPLOYEES
WHERE
  Id = @Id";

            cmd.Parameters.AddWithValue("@Id", id);

            try
            {
                conn.Open();
                cmd.ExecuteNonQuery();
            }
            finally
            {
                conn.Close();
            }
        }

        public Employee GetByUsernameAndPassword(string username, string password)
        {
            Employee item = null;

            SqlConnection conn = new SqlConnection();
            conn.ConnectionString = @"Server=DESKTOP-DK0K62G\SQLEXPRESS;Database=AutoTrade;User Id=sa;Password=010698;";

            SqlCommand cmd = new SqlCommand();
            cmd.Connection = conn;
            cmd.CommandText = @"
SELECT
  Id,
  Username,
  Password,
  FirstName,
  LastName,
  Phone,
  Email
FROM
  EMPLOYEES
WHERE
  Username = @Username AND
  Password = @Password
";

            cmd.Parameters.AddWithValue("@Username", username);
            cmd.Parameters.AddWithValue("@Password", password);

            SqlDataReader reader = null;
            try
            {
                conn.Open();
                reader = cmd.ExecuteReader();
                if (reader.Read())
                {
                    item = new Employee();
                    item.Id = Convert.ToInt32(reader["Id"]);
                    item.Username = Convert.ToString(reader["Username"]);
                    item.Password = Convert.ToString(reader["Password"]);
                    item.FirstName = Convert.ToString(reader["FirstName"]);
                    item.LastName = Convert.ToString(reader["LastName"]);
                    item.Phone = Convert.ToString(reader["Phone"]);
                    item.Email = Convert.ToString(reader["Email"]);
                }
            }
            finally
            {
                reader.Close();
                conn.Close();
            }

            return item;
        }
    }
}