using AutoTradeAndRentPlatform.Entities;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Web;

namespace AutoTradeAndRentPlatform.Repositories
{
    public class CustomersRepository
    {
        public List<Customer> GetAll()
        {
            List<Customer> items = new List<Customer>();

            SqlConnection conn = new SqlConnection();
            conn.ConnectionString = @"Server=DESKTOP-DK0K62G\SQLEXPRESS;Database=AutoTrade;User Id=sa;Password=010698;";

            SqlCommand cmd = new SqlCommand();
            cmd.Connection = conn;
            cmd.CommandText = @"
SELECT
  Id,
  FirstName,
  LastName,
  EGN,
  Phone,
  Address,
  EmployeeId
FROM
  CUSTOMERS
";
            SqlDataReader reader = null;
            try
            {
                conn.Open();
                reader = cmd.ExecuteReader();
                while (reader.Read())
                {
                    Customer item = new Customer();
                    item.Id = Convert.ToInt32(reader["Id"]);
                    item.FirstName = Convert.ToString(reader["FirstName"]);
                    item.LastName = Convert.ToString(reader["LastName"]);
                    item.EGN = Convert.ToString(reader["EGN"]);
                    item.Phone = Convert.ToString(reader["Phone"]);
                    item.Address = Convert.ToString(reader["Address"]);
                    item.EmployeeId = Convert.ToInt32(reader["EmployeeId"]);
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
        public List<Customer> GetAll(int parentEmployeeId)
        {
            List<Customer> items = new List<Customer>();

            SqlConnection conn = new SqlConnection();
            conn.ConnectionString = @"Server=DESKTOP-DK0K62G\SQLEXPRESS;Database=AutoTrade;User Id=sa;Password=010698;";

            SqlCommand cmd = new SqlCommand();
            cmd.Connection = conn;
            cmd.CommandText = @"
SELECT
  Id,
  FirstName,
  LastName,
  EGN,
  Phone,
  Address,
  EmployeeId
FROM
  CUSTOMERS
WHERE
  EmployeeId = @EmployeeId
";

            cmd.Parameters.AddWithValue("@EmployeeId", parentEmployeeId);

            SqlDataReader reader = null;
            try
            {
                conn.Open();
                reader = cmd.ExecuteReader();
                while (reader.Read())
                {
                    Customer item = new Customer();
                    item.Id = Convert.ToInt32(reader["Id"]);
                    item.FirstName = Convert.ToString(reader["FirstName"]);
                    item.LastName = Convert.ToString(reader["LastName"]);
                    item.EGN = Convert.ToString(reader["EGN"]);
                    item.Phone = Convert.ToString(reader["Phone"]);
                    item.Address = Convert.ToString(reader["Address"]);
                    item.EmployeeId = Convert.ToInt32(reader["EmployeeId"]);
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

        public void Insert(Customer item)
        {
            SqlConnection conn = new SqlConnection();
            conn.ConnectionString = @"Server=DESKTOP-DK0K62G\SQLEXPRESS;Database=AutoTrade;User Id=sa;Password=010698;";

            SqlCommand cmd = new SqlCommand();
            cmd.Connection = conn;
            cmd.CommandText = @"
INSERT INTO CUSTOMERS(
  FirstName,
  LastName,
  EGN,
  Phone,
  Address,
  EmployeeId
)
VALUES (
  @FirstName,
  @LastName,
  @EGN,
  @Phone,
  @Address,
  @EmployeeId
)";

            cmd.Parameters.AddWithValue("@FirstName", item.FirstName);
            cmd.Parameters.AddWithValue("@LastName", item.LastName);
            cmd.Parameters.AddWithValue("@EGN", item.EGN);
            cmd.Parameters.AddWithValue("@Phone", item.Phone);
            cmd.Parameters.AddWithValue("@Address", item.Address);
            cmd.Parameters.AddWithValue("@EmployeeId", item.EmployeeId);

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

        public void Update(Customer item)
        {
            SqlConnection conn = new SqlConnection();
            conn.ConnectionString = @"Server=DESKTOP-DK0K62G\SQLEXPRESS;Database=AutoTrade;User Id=sa;Password=010698;";

            SqlCommand cmd = new SqlCommand();
            cmd.Connection = conn;
            cmd.CommandText = @"
UPDATE CUSTOMERS SET
  FirstName = @FirstName,
  LastName = @LastName,
  EGN = @EGN,
  Phone = @Phone,
  Address = @Address,
  EmployeeId=@EmployeeId
WHERE
  Id = @Id";

            cmd.Parameters.AddWithValue("@FirstName", item.FirstName);
            cmd.Parameters.AddWithValue("@LastName", item.LastName);
            cmd.Parameters.AddWithValue("@EGN", item.EGN);
            cmd.Parameters.AddWithValue("@Phone", item.Phone);
            cmd.Parameters.AddWithValue("@Address", item.Address);
            cmd.Parameters.AddWithValue("@EmployeeId", item.EmployeeId);
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
DELETE FROM CUSTOMERS
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

        public Customer GetById(int id)
        {
            Customer item = null;

            SqlConnection conn = new SqlConnection();
            conn.ConnectionString = @"Server=DESKTOP-DK0K62G\SQLEXPRESS;Database=AutoTrade;User Id=sa;Password=010698;";

            SqlCommand cmd = new SqlCommand();
            cmd.Connection = conn;
            cmd.CommandText = @"
SELECT
  Id,
  FirstName,
  LastName,
  EGN,
  Phone,
  Address,
  EmployeeId
FROM
  CUSTOMERS
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
                    item = new Customer();
                    item.Id = Convert.ToInt32(reader["Id"]);
                    item.FirstName = Convert.ToString(reader["FirstName"]);
                    item.LastName = Convert.ToString(reader["LastName"]);
                    item.EGN = Convert.ToString(reader["EGN"]);
                    item.Phone = Convert.ToString(reader["Phone"]);
                    item.Address = Convert.ToString(reader["Address"]);
                    item.EmployeeId = Convert.ToInt32(reader["EmployeeId"]);

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