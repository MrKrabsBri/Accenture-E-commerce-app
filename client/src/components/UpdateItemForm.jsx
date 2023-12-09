import React, { useState } from "react";
import { useForm } from "react-hook-form";
import { updateItem } from "../services/api";
import {
  TextField,
  Typography,
  Button,
  CircularProgress,
  Card,
  CardContent,
  Container,
  Box,
  Paper,
} from "@mui/material";
import { useNavigate } from "react-router-dom";

const UpdateItemForm = ({ itemData }) => {
  const { register, handleSubmit, setValue } = useForm();
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  React.useEffect(() => {
    Object.keys(itemData).forEach((key) => {
      setValue(key, itemData[key]);
    });
  }, [itemData, setValue]);

  const onSubmit = async (data) => {
    try {
      setLoading(true);
      await updateItem(itemData.itemId, data);
      navigate("/");
    } catch (error) {
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
            <TextField
              label="Item Image"
              {...register("itemImage")}
              fullWidth
              margin="normal"
            />
            <Button
              type="submit"
              variant="contained"
              disabled={loading}
              fullWidth
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
