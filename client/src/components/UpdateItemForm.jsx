import React, { useState, useEffect } from "react";
import { useForm, Controller } from "react-hook-form";
import { updateItem } from "../services/api";
import {
  TextField,
  Typography,
  Button,
  CircularProgress,
  Container,
  Box,
  Paper,
} from "@mui/material";
import { useNavigate } from "react-router-dom";
import { useSnackbar } from "../components/CustomSnackbarContext";
const UpdateItemForm = ({ itemData }) => {
  const { register, handleSubmit, setValue } = useForm();
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();
  const { showSnackbar } = useSnackbar();
  const [selectedFile, setSelectedFile] = useState(null);

  useEffect(() => {
    Object.keys(itemData).forEach((key) => {
      setValue(key, itemData[key]);
    });

    setSelectedFile(itemData.itemImage);
  }, [itemData, setValue]);

  const handleUploadClick = () => {
    document.getElementById("hiddenFileInput").click();
  };

  const handleFileChange = (event) => {
    const file = event.target.files[0];

    const reader = new FileReader();
    reader.onloadend = () => {
      try {
        setSelectedFile(reader.result);
        showSnackbar("File chosen successfully", "success");
      } catch (error) {
        showSnackbar(
          "Error occurred while processing the file. Please try again.",
          "error"
        );
      }
    };

    try {
      reader.readAsDataURL(file);
    } catch (error) {
      showSnackbar(
        "Error occurred while reading the file. Please try again.",
        "error"
      );
    }
  };

  const onSubmit = async (data) => {
    try {
      setLoading(true);

      if (Object.values(data).some((value) => value === "")) {
        showSnackbar("All fields must be filled out.", "error");
        setLoading(false);
        return;
      }

      const positiveFields = ["price", "quantityAvailable"];
      const negativeFields = positiveFields.filter(
        (field) => parseFloat(data[field]) <= 0
      );

      if (negativeFields.length > 0) {
        const errorField =
          negativeFields[0] === "price" ? "Price" : "Quantity Available";
        showSnackbar(
          `${errorField} should be a positive value. Please correct.`,
          "error"
        );
        setLoading(false);
        return;
      }

      await updateItem(itemData.itemId, {
        ...data,
        itemImage: selectedFile,
      });
      showSnackbar("Item updated successfully", "success");
      navigate("/");
    } catch (error) {
      showSnackbar("An error occurred. Please try again later.", "error");
    } finally {
      setLoading(false);
    }
  };

  return (
    <Container component="main" maxWidth="xs">
      <Box
        display="flex"
        flexDirection="column"
        justifyContent="center"
        alignItems="center"
        height="100vh"
      >
        <Box mt={4} mb={4}>
          <Typography variant="h4" align="center">
            Update Item
          </Typography>
        </Box>
        <Paper elevation={3} style={{ padding: "20px", width: "100%" }}>
          <form onSubmit={handleSubmit(onSubmit)}>
            <TextField
              label="Item Name"
              {...register("itemName")}
              fullWidth
              margin="normal"
            />
            <TextField
              label="Size"
              {...register("size")}
              fullWidth
              margin="normal"
            />
            <TextField
              label="Description"
              {...register("description")}
              fullWidth
              margin="normal"
            />
            <TextField
              label="Price"
              {...register("price")}
              type="text"
              fullWidth
              margin="normal"
            />
            <TextField
              label="Quantity Available"
              {...register("quantityAvailable")}
              type="number"
              fullWidth
              margin="normal"
            />
            <input
              type="file"
              id="hiddenFileInput"
              onChange={handleFileChange}
              style={{ display: "none" }}
            />
            {selectedFile && (
              <Box mt={2} textAlign="center">
                <img
                  src={selectedFile}
                  alt="Uploaded"
                  style={{ maxWidth: "100%", maxHeight: "200px" }}
                />
              </Box>
            )}
            <Button
              variant="contained"
              color="primary"
              fullWidth
              onClick={handleUploadClick}
              style={{ marginTop: "15px" }}
            >
              Change Image
            </Button>
            <Button
              type="submit"
              variant="contained"
              disabled={loading}
              fullWidth
              style={{ marginTop: "15px" }}
            >
              {loading ? (
                <CircularProgress size={24} color="inherit" />
              ) : (
                "Update Item"
              )}
            </Button>
          </form>
        </Paper>
      </Box>
    </Container>
  );
};

export default UpdateItemForm;
