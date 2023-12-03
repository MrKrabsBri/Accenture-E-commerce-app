import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import {
  Typography,
  Button,
  Grid,
  Paper,
  Container,
  Box,
  FormControl,
  InputLabel,
  OutlinedInput,
} from "@mui/material";
import { addItem } from "../services/api";

const AddItem = () => {
  const navigate = useNavigate();
  const [itemData, setItemData] = useState({
    itemName: "",
    itemImage: "",
    size: "",
    description: "",
    price: "",
    quantityAvailable: "",
  });

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setItemData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const addedItem = await addItem(itemData);
      console.log("Item added successfully:", addedItem);
      navigate("/");
      setItemData({
        itemName: "",
        itemImage: "",
        size: "",
        description: "",
        price: "",
        quantityAvailable: "",
      });
    } catch (error) {
      console.error("Error adding item:", error);
    }
  };

  return (
    <Container component="main" maxWidth="xs">
      <Box
        display="flex"
        flexDirection="column"
        alignItems="center"
        minHeight="100vh"
      >
        <Box mt={4} mb={4}>
          <Typography variant="h4" align="center">
            Add Item
          </Typography>
        </Box>
        <Paper elevation={3} style={{ padding: "20px", width: "100%" }}>
          <form onSubmit={handleSubmit}>
            <Grid container spacing={2}>
              <Grid item xs={12}>
                <FormControl fullWidth variant="outlined">
                  <InputLabel htmlFor="itemName">Item Name</InputLabel>
                  <OutlinedInput
                    id="itemName"
                    label="Item Name"
                    name="itemName"
                    value={itemData.itemName}
                    onChange={handleInputChange}
                  />
                </FormControl>
              </Grid>
              <Grid item xs={12}>
                <FormControl fullWidth variant="outlined">
                  <InputLabel htmlFor="itemImage">Item Image URL</InputLabel>
                  <OutlinedInput
                    id="itemImage"
                    label="Item Image URL"
                    name="itemImage"
                    value={itemData.itemImage}
                    onChange={handleInputChange}
                  />
                </FormControl>
              </Grid>
              <Grid item xs={12}>
                <FormControl fullWidth variant="outlined">
                  <InputLabel htmlFor="size">Size</InputLabel>
                  <OutlinedInput
                    id="size"
                    label="Size"
                    name="size"
                    value={itemData.size}
                    onChange={handleInputChange}
                  />
                </FormControl>
              </Grid>
              <Grid item xs={12}>
                <FormControl fullWidth variant="outlined">
                  <InputLabel htmlFor="description">Description</InputLabel>
                  <OutlinedInput
                    id="description"
                    label="Description"
                    name="description"
                    value={itemData.description}
                    onChange={handleInputChange}
                  />
                </FormControl>
              </Grid>
              <Grid item xs={12}>
                <FormControl fullWidth variant="outlined">
                  <InputLabel htmlFor="price">Price</InputLabel>
                  <OutlinedInput
                    id="price"
                    label="Price"
                    name="price"
                    value={itemData.price}
                    onChange={handleInputChange}
                  />
                </FormControl>
              </Grid>
              <Grid item xs={12}>
                <FormControl fullWidth variant="outlined">
                  <InputLabel htmlFor="quantityAvailable">
                    Quantity Available
                  </InputLabel>
                  <OutlinedInput
                    id="quantityAvailable"
                    label="Quantity Available"
                    name="quantityAvailable"
                    value={itemData.quantityAvailable}
                    onChange={handleInputChange}
                  />
                </FormControl>
              </Grid>
            </Grid>
            <Grid item xs={12} pt={2}>
              <Button variant="contained" color="primary" type="submit">
                Add Item
              </Button>
            </Grid>
          </form>
        </Paper>
      </Box>
    </Container>
  );
};

export default AddItem;
