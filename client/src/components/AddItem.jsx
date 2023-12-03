import React, { useState } from "react";
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

const AddItem = () => {
  const [itemData, setItemData] = useState({
    item_name: "",
    item_image: "",
    size: "",
    description: "",
    price: "",
    quantity_available: "",
  });

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setItemData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log("Item data to be sent:", itemData);
    setItemData({
      item_name: "",
      item_image: "",
      size: "",
      description: "",
      price: "",
      quantity_available: "",
    });
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
                  <InputLabel htmlFor="item_name">Item Name</InputLabel>
                  <OutlinedInput
                    id="item_name"
                    label="Item Name"
                    name="item_name"
                    value={itemData.item_name}
                    onChange={handleInputChange}
                  />
                </FormControl>
              </Grid>
              <Grid item xs={12}>
                <FormControl fullWidth variant="outlined">
                  <InputLabel htmlFor="item_image">Item Image URL</InputLabel>
                  <OutlinedInput
                    id="item_image"
                    label="Item Image URL"
                    name="item_image"
                    value={itemData.item_image}
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
                  <InputLabel htmlFor="quantity_available">
                    Quantity Available
                  </InputLabel>
                  <OutlinedInput
                    id="quantity_available"
                    label="Quantity Available"
                    name="quantity_available"
                    value={itemData.quantity_available}
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
