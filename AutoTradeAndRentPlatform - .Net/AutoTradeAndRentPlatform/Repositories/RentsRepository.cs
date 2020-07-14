using AutoTradeAndRentPlatform.Entities;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Web;

namespace AutoTradeAndRentPlatform.Repositories
{
    public class RentsRepository
    {
        public Rent GetById(int id)
        {
            Rent item = null;

            SqlConnection conn = new SqlConnection();
            conn.ConnectionString = @"Server=DESKTOP-DK0K62G\SQLEXPRESS;Database=AutoTrade;User Id=sa;Password=010698;";

            SqlCommand cmd = new SqlCommand();
            cmd.Connection = conn;
            cmd.CommandText = @"
 SELECT
  R.Id,
  CarId,
  CustomerId,
  RentStart,
  RentEnd,
  PriceInTotal,
  R.EmployeeId,
  E.FirstName+' '+E.LastName AS EMPLOYEE_NAME
FROM
  RENTS R JOIN EMPLOYEES E
  ON R.EmployeeId=E.Id
WHERE
  R.Id = @Id
";

            cmd.Parameters.AddWithValue("@Id", id);

            SqlDataReader reader = null;
            try
            {
                conn.Open();
                reader = cmd.ExecuteReader();
                if (reader.Read())
                {
                    item = new Rent();
                    item.Id = Convert.ToInt32(reader["Id"]);
                    item.CarId = Convert.ToInt32(reader["CarId"]);
                    item.CustomerId = Convert.ToInt32(reader["CustomerId"]);
                    item.RentStartDate = Convert.ToDateTime(reader["RentStart"]);
                    item.RentEndDate = Convert.ToDateTime(reader["RentEnd"]);
                    item.PriceInTotal = Convert.ToDecimal(reader["PriceInTotal"]);
                    item.EmployeeId = Convert.ToInt32(reader["EmployeeId"]);
                    item.EmployeeName = Convert.ToString(reader["EMPLOYEE_NAME"]);

                }
            }
            finally
            {
                reader.Close();
                conn.Close();
            }

            return item;
        }
        public List<Rent> GetAll()
        {
            List<Rent> items = new List<Rent>();

            SqlConnection conn = new SqlConnection();
            conn.ConnectionString = @"Server=DESKTOP-DK0K62G\SQLEXPRESS;Database=AutoTrade;User Id=sa;Password=010698;";

            SqlCommand cmd = new SqlCommand();
            cmd.Connection = conn;
            cmd.CommandText = @"
  SELECT
  R.Id,
  CarId,
  CustomerId,
  RentStart,
  RentEnd,
  PriceInTotal,
  R.EmployeeId,
  E.FirstName+' '+E.LastName AS EMPLOYEE_NAME
FROM
  RENTS R JOIN EMPLOYEES E
  ON R.EmployeeId=E.Id
";

            SqlDataReader reader = null;
            try
            {
                conn.Open();
                reader = cmd.ExecuteReader();
                while (reader.Read())
                {
                    Rent item = new Rent();
                    item.Id = Convert.ToInt32(reader["Id"]);
                    item.CarId = Convert.ToInt32(reader["CarId"]);
                    item.CustomerId = Convert.ToInt32(reader["CustomerId"]);
                    item.RentStartDate = Convert.ToDateTime(reader["RentStart"]);
                    item.RentEndDate = Convert.ToDateTime(reader["RentEnd"]);
                    item.PriceInTotal = Convert.ToDecimal(reader["PriceInTotal"]);
                    item.EmployeeId = Convert.ToInt32(reader["EmployeeId"]);
                    item.EmployeeName = Convert.ToString(reader["EMPLOYEE_NAME"]);
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
        public void Insert(Rent item)
        {
            SqlConnection conn = new SqlConnection();
            conn.ConnectionString = @"Server=DESKTOP-DK0K62G\SQLEXPRESS;Database=AutoTrade;User Id=sa;Password=010698;";

            SqlCommand cmd = new SqlCommand();
            cmd.Connection = conn;
            cmd.CommandText = @"
INSERT INTO RENTS(
  CarId,
  CustomerId,
  RentStart,
  RentEnd,
  PriceInTotal,
  EmployeeId
)
VALUES (
  @CarId,
  @CustomerId,
  @RentStart,
  @RentEnd,
  @PriceInTotal,
  @EmployeeId
)";

            cmd.Parameters.AddWithValue("@CarId", item.CarId);
            cmd.Parameters.AddWithValue("@CustomerId", item.CustomerId);
            cmd.Parameters.AddWithValue("@RentStart", item.RentStartDate);
            cmd.Parameters.AddWithValue("@RentEnd", item.RentEndDate);
            cmd.Parameters.AddWithValue("@PriceInTotal", item.PriceInTotal);
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
        public void Update(Rent item)
        {
            SqlConnection conn = new SqlConnection();
            conn.ConnectionString = @"Server=DESKTOP-DK0K62G\SQLEXPRESS;Database=AutoTrade;User Id=sa;Password=010698;";

            SqlCommand cmd = new SqlCommand();
            cmd.Connection = conn;
            cmd.CommandText = @"
UPDATE RENTS SET
  CarId = @CarId,
  CustomerId = @CustomerId,
  RentStart = @RentStart,
  RentEnd = @RentEnd,
  PriceInTotal = @PriceInTotal
WHERE
  Id = @Id";

            cmd.Parameters.AddWithValue("@CarId", item.CarId);
            cmd.Parameters.AddWithValue("@CustomerId", item.CustomerId);
            cmd.Parameters.AddWithValue("@RentStart", item.RentStartDate);
            cmd.Parameters.AddWithValue("@RentEnd", item.RentEndDate);
            cmd.Parameters.AddWithValue("@PriceInTotal", item.PriceInTotal);
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
DELETE FROM RENTS
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

    }
}