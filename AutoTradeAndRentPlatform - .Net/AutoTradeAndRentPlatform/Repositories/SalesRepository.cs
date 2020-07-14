using AutoTradeAndRentPlatform.Entities;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Web;

namespace AutoTradeAndRentPlatform.Repositories
{
    public class SalesRepository
    {

        public List<Sale> GetAll()
        {
            List<Sale> items = new List<Sale>();

            SqlConnection conn = new SqlConnection();
            conn.ConnectionString = @"Server=DESKTOP-DK0K62G\SQLEXPRESS;Database=AutoTrade;User Id=sa;Password=010698;";

            SqlCommand cmd = new SqlCommand();
            cmd.Connection = conn;
            cmd.CommandText = @"
  SELECT
  S.Id,
  CarId,
  CarName,
  CustomerId,
  SaleDate,
  S.EmployeeId,
  E.FirstName+' '+E.LastName AS EMPLOYEE_NAME
FROM
  SALES S JOIN EMPLOYEES E
  ON S.EmployeeId=E.Id
ORDER BY SaleDate DESC
";

            SqlDataReader reader = null;
            try
            {
                conn.Open();
                reader = cmd.ExecuteReader();
                while (reader.Read())
                {
                    Sale item = new Sale();
                    item.Id = Convert.ToString(reader["Id"]);
                    item.CarId = Convert.ToInt32(reader["CarId"]);
                    item.CarName = Convert.ToString(reader["CarName"]);
                    item.CustomerId = Convert.ToInt32(reader["CustomerId"]);
                    item.SaleDate = Convert.ToDateTime(reader["SaleDate"]);
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
        public void Insert(Sale item)
        {
            SqlConnection conn = new SqlConnection();
            conn.ConnectionString = @"Server=DESKTOP-DK0K62G\SQLEXPRESS;Database=AutoTrade;User Id=sa;Password=010698;";

            SqlCommand cmd = new SqlCommand();
            cmd.Connection = conn;
            cmd.CommandText = @"
INSERT INTO SALES(
  Id,
  CarId,
  CarName,
  CustomerId,
  SaleDate,
  EmployeeId
)
VALUES (
  @Id,
  @CarId,
  @CarName,
  @CustomerId,
  @SaleDate,
  @EmployeeId
)";

            cmd.Parameters.AddWithValue("@Id", item.Id);
            cmd.Parameters.AddWithValue("@CarId", item.CarId);
            cmd.Parameters.AddWithValue("@CarName", item.CarName);
            cmd.Parameters.AddWithValue("@CustomerId", item.CustomerId);
            cmd.Parameters.AddWithValue("@SaleDate", item.SaleDate);
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
    }
}