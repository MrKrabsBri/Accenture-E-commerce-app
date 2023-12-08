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
import { useSnackbar } from "../components/CustomSnackbarContext";
const AddItem = () => {
  const [selectedFile, setSelectedFile] = useState(null);
  const [selectedImageName, setSelectedImageName] = useState("");
  const navigate = useNavigate();
  const { showSnackbar } = useSnackbar();
  const [itemData, setItemData] = useState({
    itemName: "",
    itemImage: "",
    size: "",
    description: "",
    price: "",
    quantityAvailable: "",
  });

  const isImageUploaded = itemData.itemImage !== "";

  const isAddButtonDisabled = Object.values(itemData).some(
    (value) => value === ""
  );

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setItemData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  const handleUploadClick = () => {
    document.getElementById("hiddenFileInput").click();
  };

  const handleFileChange = (event) => {
    const file = event.target.files[0];
    setSelectedImageName(file.name);
    const reader = new FileReader();
    reader.onloadend = () => {
      try {
        setItemData((prevData) => ({
          ...prevData,
          itemImage: reader.result,
        }));
      } catch (error) {
        showSnackbar("Something went wrong... Try again", "error");
      }
    };

    try {
      reader.readAsDataURL(file);
      showSnackbar("File chosen successfully", "success");
    } catch (error) {
      showSnackbar("Something went wrong... Try again", "error");
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const positiveFields = ["price", "quantityAvailable"];
    const negativeFields = positiveFields.filter(
      (field) => parseFloat(itemData[field]) <= 0
    );

    if (negativeFields.length === 2) {
      showSnackbar(
        `Price and Quantity Available should be positive values. Please correct them.`,
        "error"
      );
      return;
    }

    if (negativeFields.length === 1) {
      const errorField =
        negativeFields[0] === "price" ? "Price" : "Quantity Available";
      showSnackbar(
        `${errorField} should be a positive value. Please correct.`,
        "error"
      );
      return;
    }

    console.log("Item data:", itemData);
    try {
      const addedItem = await addItem(itemData);
      console.log("Item added successfully:", addedItem);
      showSnackbar("Item added successfully", "success");
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
      showSnackbar("Error adding item", "error");
      console.error("Error adding item:", error);
    }
  };

  return (
    <Container component="main" maxWidth="xs">
      <input
        type="file"
        id="hiddenFileInput"
        onChange={handleFileChange}
        style={{ display: "none" }}
      />
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
            {isImageUploaded && (
              <React.Fragment>
                <Box mt={2} textAlign="center">
                  <Typography
                    variant="body1"
                    align="center"
                    style={{ marginTop: 10 }}
                  >
                    {selectedImageName}
                  </Typography>
                  <img
                    src={itemData.itemImage}
                    alt="Uploaded"
                    style={{ maxWidth: "100%", maxHeight: "200" }}
                  />
                </Box>
              </React.Fragment>
            )}
            <Grid item container justifyContent="space-between" xs={12} pt={2}>
              <Button
                variant="contained"
                color="primary"
                type="submit"
                disabled={isAddButtonDisabled}
              >
                Add Item
              </Button>
              <Button
                variant="contained"
                color="primary"
                onClick={handleUploadClick}
                style={{ marginLeft: "auto" }}
              >
                Upload Image
              </Button>
            </Grid>
          </form>
        </Paper>
      </Box>
    </Container>
  );
};

export default AddItem;
