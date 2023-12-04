import React, { useState, useEffect } from "react";
import { Grid } from "@mui/material";
import ItemCard from "./ItemCard";
import { getItems, deleteItem } from "../services/api";
import { useSnackbar } from "../components/CustomSnackbarContext";
const ItemList = () => {
  const [items, setItems] = useState([]);
  const { showSnackbar } = useSnackbar();
  useEffect(() => {
    const fetchItems = async () => {
      try {
        const itemsData = await getItems();
        setItems(itemsData);
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
      showSnackbar("Item has been deleted successfully", "success");
    } catch (error) {
      console.error("Error deleting item:", error);
      showSnackbar("Error deleting item", "error");
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
