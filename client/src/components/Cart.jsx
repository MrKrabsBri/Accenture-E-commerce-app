import React, { useState } from "react";
import {
  Typography,
  Grid,
  Paper,
  Container,
  Box,
  Divider,
  IconButton,
  Dialog,
  DialogTitle,
  DialogActions,
  Button,
  CardMedia,
} from "@mui/material";
import { useSnackbar } from "../components/CustomSnackbarContext";
import DeleteOutlineIcon from "@mui/icons-material/DeleteOutline";
import { removeItemFromCart } from "../services/api";

const Cart = ({ item, quantity, userId }) => {
  const [openDialog, setOpenDialog] = useState(false);
  const { showSnackbar } = useSnackbar();

  const handleDeleteClick = () => {
    setOpenDialog(true);
  };

  const handleConfirmDelete = async () => {
    try {
      await removeItemFromCart(userId, item.itemId);
      showSnackbar("Item has been deleted successfully", "success");
      setOpenDialog(false);
    } catch (error) {
      showSnackbar("Error deleting item", "error");
    } finally {
      setOpenDialog(false);
    }
  };

  const handleCancelDelete = () => {
    setOpenDialog(false);
  };
  return (
    <Container component="main" maxWidth="md">
      <Box
        display="flex"
        flexDirection="column"
        alignItems="center"
        mt={4}
        mb={4}
      >
        <Dialog open={openDialog} onClose={handleCancelDelete}>
          <DialogTitle>Delete Item</DialogTitle>
          <DialogTitle>
            {"Are you sure you want to delete this item?"}
          </DialogTitle>
          <DialogActions>
            <Button onClick={handleCancelDelete} color="primary">
              Cancel
            </Button>
            <Button onClick={handleConfirmDelete} color="error">
              Delete
            </Button>
          </DialogActions>
        </Dialog>
        <Paper elevation={3} style={{ padding: "20px", width: "100%" }}>
          <Grid container alignItems="center">
            <Grid item xs={12}>
              <Grid container spacing={1}>
                <Grid item xs={12} sm={3}>
                  <CardMedia
                    component="img"
                    src={item.itemImage}
                    alt={item.name}
                    style={{
                      width: "80%",
                      height: "auto",
                    }}
                  />
                </Grid>
                <Grid item xs={12} sm={3}>
                  <Typography
                    variant="h6"
                    style={{
                      fontWeight: "bold",
                    }}
                  >
                    {item.name}
                  </Typography>
                  <Typography variant="body1">
                    <strong> Name: </strong> {item.itemName}
                  </Typography>
                  <Typography variant="body1">Size: {item.size}</Typography>
                  <Typography variant="body1">
                    In Stock: {item.quantityAvailable}
                  </Typography>
                  <br />
                  <Typography variant="body1">
                    <strong>Description:</strong>
                  </Typography>
                  <Typography variant="body1">{item.description}</Typography>
                </Grid>
                <Grid item xs={12} sm={3}>
                  <Box pt={2.9}>
                    <Typography variant="body1">
                      Price: {item.price}€ each
                    </Typography>
                    <Typography variant="body1">
                      Quantity: {quantity}
                    </Typography>
                  </Box>
                </Grid>
                <Grid item xs={6} sm={1}>
                  <Divider orientation="vertical" />
                </Grid>
                <Grid item xs={12} sm={2}>
                  <Box display="flex" alignItems="center" pt={10}>
                    <Typography variant="body1">
                      <strong>Total: {item.price * quantity}€</strong>
                    </Typography>
                    <IconButton onClick={handleDeleteClick}>
                      <DeleteOutlineIcon color="error" />
                    </IconButton>
                  </Box>
                </Grid>
              </Grid>

              <Divider
                style={{
                  width: "100%",
                  marginTop: "20px",
                  marginBottom: "10px",
                }}
              />
            </Grid>
          </Grid>
        </Paper>
      </Box>
    </Container>
  );
};

export default Cart;
