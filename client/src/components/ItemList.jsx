import React, { useState, useEffect } from "react";
import axios from "axios";
import { Grid } from "@mui/material";
import ItemCard from "./ItemCard";
import { deleteItem } from "../services/api";

const API_BASE_URL = "http://localhost:8080/api";

const api = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    "Content-Type": "application/json",
  },
  withCredentials: true,
});

const ItemList = () => {
  const [items, setItems] = useState([]);

  useEffect(() => {
    const fetchItems = async () => {
      try {
        const response = await api.get("/items");
        setItems(response.data);
      } catch (error) {
        console.error("Error fetching items:", error);
      }
    };

    fetchItems();
  }, []);

  const handleDelete = async (itemId) => {
    try {
      await deleteItem(itemId);
      setItems((prevItems) =>
        prevItems.filter((item) => item.itemId !== itemId)
      );
    } catch (error) {
      console.error("Error deleting item:", error);
    }
  };

  return (
    <Grid container spacing={2}>
      {items.map((item, index) => (
        <Grid item key={index} xs={12} sm={6} md={3}>
          <ItemCard item={item} onDelete={handleDelete} />
        </Grid>
      ))}
    </Grid>
  );
};

export default ItemList;
