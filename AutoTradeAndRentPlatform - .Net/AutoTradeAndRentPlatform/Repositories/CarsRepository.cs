using AutoTradeAndRentPlatform.Entities;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Web;

namespace AutoTradeAndRentPlatform.Repositories
{
    public class CarsRepository
    {

        public Car GetById(int id)
        {
            Car item = null;

            SqlConnection conn = new SqlConnection();
            conn.ConnectionString = @"Server=DESKTOP-DK0K62G\SQLEXPRESS;Database=AutoTrade;User Id=sa;Password=010698;";

            SqlCommand cmd = new SqlCommand();
            cmd.Connection = conn;
            cmd.CommandText = @"
SELECT
  Id,
  Make,
  Model,
  Year,
  Price,
  Color
FROM
  CARS
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
                    item = new Car();
                    item.Id = Convert.ToInt32(reader["Id"]);
                    item.Make = Convert.ToString(reader["Make"]);
                    item.Model = Convert.ToString(reader["Model"]);
                    item.Year = Convert.ToInt32(reader["Year"]);
                    item.Price = Convert.ToDecimal(reader["Price"]);
                    item.Color = Convert.ToString(reader["Color"]);

                }
            }
            finally
            {
                reader.Close();
                conn.Close();
            }

            return item;
        }

        public List<Car> GetAll()
        {
            List<Car> items = new List<Car>();

            SqlConnection conn = new SqlConnection();
            conn.ConnectionString = @"Server=DESKTOP-DK0K62G\SQLEXPRESS;Database=AutoTrade;User Id=sa;Password=010698;";

            SqlCommand cmd = new SqlCommand();
            cmd.Connection = conn;
            cmd.CommandText = @"
SELECT
  Id,
  Make,
  Model,
  Year,
  Price,
  Color
FROM
  CARS
";

            SqlDataReader reader = null;
            try
            {
                conn.Open();
                reader = cmd.ExecuteReader();
                while (reader.Read())
                {
                    Car item = new Car();
                    item.Id = Convert.ToInt32(reader["Id"]);
                    item.Make = Convert.ToString(reader["Make"]);
                    item.Model = Convert.ToString(reader["Model"]);
                    item.Year = Convert.ToInt32(reader["Year"]);
                    item.Price = Convert.ToInt32(reader["Price"]);
                    item.Color = Convert.ToString(reader["Color"]);
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

        public void Insert(Car item)
        {
            SqlConnection conn = new SqlConnection();
            conn.ConnectionString = @"Server=DESKTOP-DK0K62G\SQLEXPRESS;Database=AutoTrade;User Id=sa;Password=010698;";

            SqlCommand cmd = new SqlCommand();
            cmd.Connection = conn;
            cmd.CommandText = @"
INSERT INTO CARS(
  Make,
  Model,
  Year,
  Price,
  Color
)
VALUES (
  @Make,
  @Model,
  @Year,
  @Price,
  @Color
)";

            cmd.Parameters.AddWithValue("@Make", item.Make);
            cmd.Parameters.AddWithValue("@Model", item.Model);
            cmd.Parameters.AddWithValue("@Year", item.Year);
            cmd.Parameters.AddWithValue("@Price", item.Price);
            cmd.Parameters.AddWithValue("@Color", item.Color);
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

        public void Update(Car item)
        {
            SqlConnection conn = new SqlConnection();
            conn.ConnectionString = @"Server=DESKTOP-DK0K62G\SQLEXPRESS;Database=AutoTrade;User Id=sa;Password=010698;";

            SqlCommand cmd = new SqlCommand();
            cmd.Connection = conn;
            cmd.CommandText = @"
UPDATE CARS SET
  Make = @Make,
  Model = @Model,
  Year = @Year,
  Price = @Price,
  Color = @Color
WHERE
  Id = @Id";

            cmd.Parameters.AddWithValue("@Make", item.Make);
            cmd.Parameters.AddWithValue("@Model", item.Model);
            cmd.Parameters.AddWithValue("@Year", item.Year);
            cmd.Parameters.AddWithValue("@Price", item.Price);
            cmd.Parameters.AddWithValue("@Color", item.Color);
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
DELETE FROM CARS
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