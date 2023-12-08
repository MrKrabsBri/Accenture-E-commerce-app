import React, { useState, useEffect } from "react";
import {
  Grid,
  Button,
  Dialog,
  DialogActions,
  DialogContent,
  DialogTitle,
  Typography,
} from "@mui/material";
import ItemCard from "./ItemCard";
import { getItems, deleteItem } from "../services/api";
import { useSnackbar } from "../components/CustomSnackbarContext";

const ItemList = () => {
  const [items, setItems] = useState([]);
  const [deleteItemId, setDeleteItemId] = useState(null);
  const { showSnackbar } = useSnackbar();

  useEffect(() => {
    const fetchItems = async () => {
      try {
        const itemsData = await getItems();
        setItems(itemsData);
      } catch (error) {}
    };

    fetchItems();
  }, []);

  const handleDelete = async (itemId) => {
    setDeleteItemId(itemId);
  };

  const handleDeleteConfirmed = async () => {
    try {
      await deleteItem(deleteItemId);
      setItems((prevItems) =>
        prevItems.filter((item) => item.itemId !== deleteItemId)
      );
      showSnackbar("Item has been deleted successfully", "success");
    } catch (error) {
      showSnackbar("Error deleting item", "error");
    } finally {
      setDeleteItemId(null);
    }
  };

  const handleDeleteCanceled = () => {
    setDeleteItemId(null);
  };

  return (
    <Grid container spacing={2}>
      {items.map((item, index) => (
        <Grid item key={index} xs={12} sm={6} md={3}>
          <ItemCard item={item} onDelete={handleDelete} />
        </Grid>
      ))}

      <Dialog
        open={Boolean(deleteItemId)}
        onClose={handleDeleteCanceled}
        PaperProps={{
          style: {
            maxWidth: "400px",
          },
        }}
      >
        <DialogTitle>Delete Item</DialogTitle>
        <DialogContent>
          <Typography>Are you sure you want to delete this item?</Typography>
        </DialogContent>
        <DialogActions>
          <Button onClick={handleDeleteCanceled} color="primary">
            Cancel
          </Button>
          <Button onClick={handleDeleteConfirmed} color="error" autoFocus>
            Delete
          </Button>
        </DialogActions>
      </Dialog>
    </Grid>
  );
};

export default ItemList;
