using AutoTradeAndRentPlatform.Entities;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Web;

namespace AutoTradeAndRentPlatform.Repositories
{
    public class RentACarRepository
    {
        public RentACar GetById(int id)
        {
            RentACar item = null;

            SqlConnection conn = new SqlConnection();
            conn.ConnectionString = @"Server=DESKTOP-DK0K62G\SQLEXPRESS;Database=AutoTrade;User Id=sa;Password=010698;";

            SqlCommand cmd = new SqlCommand();
            cmd.Connection = conn;
            cmd.CommandText = @"
SELECT
  R.Id,
  CarId,
  C.Make+' '+C.Model AS CAR_NAME,
  C.Year AS CAR_YEAR,
  PricePerDay,
  Status
FROM
  RENTACAR R JOIN CARS C
  ON R.CarId=C.Id
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
                    item = new RentACar();
                    item.Id = Convert.ToInt32(reader["Id"]);
                    item.CarId = Convert.ToInt32(reader["CarId"]);
                    item.CarName = Convert.ToString(reader["CAR_NAME"]);
                    item.CarYear = Convert.ToInt32(reader["CAR_YEAR"]);
                    item.PricePerDay = Convert.ToDecimal(reader["PricePerDay"]);
                    item.Status = Convert.ToString(reader["Status"]);

                }
            }
            finally
            {
                reader.Close();
                conn.Close();
            }

            return item;
        }

        public List<RentACar> GetAll()
        {
            List<RentACar> items = new List<RentACar>();

            SqlConnection conn = new SqlConnection();
            conn.ConnectionString = @"Server=DESKTOP-DK0K62G\SQLEXPRESS;Database=AutoTrade;User Id=sa;Password=010698;";

            SqlCommand cmd = new SqlCommand();
            cmd.Connection = conn;
            cmd.CommandText = @"
SELECT
  R.Id,
  CarId,
  C.Make+' '+C.Model AS CAR_NAME,
  C.Year AS CAR_YEAR,
  PricePerDay,
  Status
FROM
  RENTACAR R JOIN CARS C
  ON R.CarId=C.Id
ORDER BY CarId
";

            SqlDataReader reader = null;
            try
            {
                conn.Open();
                reader = cmd.ExecuteReader();
                while (reader.Read())
                {
                    RentACar item = new RentACar();
                    item.Id = Convert.ToInt32(reader["Id"]);
                    item.CarId = Convert.ToInt32(reader["CarId"]);
                    item.CarName = Convert.ToString(reader["CAR_NAME"]);
                    item.CarYear = Convert.ToInt32(reader["CAR_YEAR"]);
                    item.PricePerDay = Convert.ToDecimal(reader["PricePerDay"]);
                    item.Status = Convert.ToString(reader["Status"]);
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
        public void Insert(RentACar item)
        {
            SqlConnection conn = new SqlConnection();
            conn.ConnectionString = @"Server=DESKTOP-DK0K62G\SQLEXPRESS;Database=AutoTrade;User Id=sa;Password=010698;";

            SqlCommand cmd = new SqlCommand();
            cmd.Connection = conn;
            cmd.CommandText = @"
INSERT INTO RENTACAR(
  CarId,
  PricePerDay,
  Status
)
VALUES (
  @CarId,
  @PricePerDay,
  @Status
)";

            cmd.Parameters.AddWithValue("@CarId", item.CarId);
            cmd.Parameters.AddWithValue("@PricePerDay", item.PricePerDay);
            cmd.Parameters.AddWithValue("@Status", item.Status);
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
        public void Update(RentACar item)
        {
            SqlConnection conn = new SqlConnection();
            conn.ConnectionString = @"Server=DESKTOP-DK0K62G\SQLEXPRESS;Database=AutoTrade;User Id=sa;Password=010698;";

            SqlCommand cmd = new SqlCommand();
            cmd.Connection = conn;
            cmd.CommandText = @"
UPDATE RENTACAR SET
  CarId = @CarId,
  PricePerDay = @PricePerDay,
  Status = @Status
WHERE
  Id = @Id";

            cmd.Parameters.AddWithValue("@CarId", item.CarId);
            cmd.Parameters.AddWithValue("@PricePerDay", item.PricePerDay);
            cmd.Parameters.AddWithValue("@Status", item.Status);
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
DELETE FROM RENTACAR
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