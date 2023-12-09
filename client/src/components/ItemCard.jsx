import React, { useState } from "react";
import {
  Card,
  CardActions,
  CardContent,
  Button,
  Typography,
  CardMedia,
  Dialog,
  DialogActions,
  DialogContent,
  DialogContentText,
  DialogTitle,
  TextField,
} from "@mui/material";
import ShoppingCartIcon from "@mui/icons-material/ShoppingCart";
import { useAuth } from "../auth/AuthContext";
import { useNavigate } from "react-router-dom";
import { useSnackbar } from "../components/CustomSnackbarContext";
import { addItemToCart } from "../services/api";

const ItemCard = ({ item, onDelete }) => {
  const navigate = useNavigate();
  const { user } = useAuth();

  const { showSnackbar } = useSnackbar();
  const [openDialog, setOpenDialog] = useState(false);
  const [quantity, setQuantity] = useState(1);

  const handleUpdateItem = () => {
    navigate("/updateitem", { state: { itemData: item } });
  };
  const handleDeleteClick = () => {
    onDelete(item.itemId);
  };
  const handleAddToCart = async () => {
    if (quantity <= 0) {
      showSnackbar("Please enter a valid quantity", "error");
      return;
    }
    if (quantity > item.quantityAvailable) {
      showSnackbar(
        "There's not enough items left in the stock, try lowering the quantity",
        "error"
      );
      return;
    }
    try {
      const user = JSON.parse(localStorage.getItem("authenticatedUser"));
      if (!user) {
        showSnackbar("You must be logged in to add items to the cart", "error");
        return;
      }

      const userData = {
        userId: user.userId,
        itemId: item.itemId,
        quantity: quantity,
      };

      await addItemToCart(userData);
      showSnackbar("Item has been added to the cart", "success");
      setOpenDialog(false);
    } catch (error) {
      showSnackbar("Failed to add item to cart", "error");
    }
  };

  const handleOpenDialog = () => {
    setOpenDialog(true);
  };

  const handleCloseDialog = () => {
    setOpenDialog(false);
  };

  return (
    <Card sx={{ mx: 2 }} style={{ position: "relative", marginBottom: "32px" }}>
      <CardMedia
        component="img"
        alt={item.itemName}
        src={item.itemImage}
        sx={{ aspectRatio: "auto", width: 1 }}
      />
      <CardContent style={{ height: "125px" }}>
        <Typography variant="h5" component="div">
          {item.itemName}
        </Typography>
        <Typography variant="body2" color="text.secondary">
          Size: {item.size}
        </Typography>
        <Typography variant="body2" color="text.secondary">
          Price: {item.price}
        </Typography>
      </CardContent>
      {user && user.userType === "ADMIN" ? (
        <CardActions
          style={{ display: "flex", justifyContent: "space-between" }}
        >
          <Button
            variant="contained"
            color="secondary"
            onClick={handleUpdateItem}
          >
            UPDATE
          </Button>
          <Button
            variant="contained"
            color="error"
            style={{ marginLeft: "8px" }}
            onClick={handleDeleteClick}
          >
            DELETE
          </Button>
        </CardActions>
      ) : null}
      <CardActions>
        <Button variant="contained" fullWidth>
          DETAILS
        </Button>
      </CardActions>
      {user && (user.userType === "ADMIN" || user.userType === "USER") ? (
        <CardActions>
          <Button
            variant="contained"
            color="success"
            fullWidth
            startIcon={<ShoppingCartIcon />}
            onClick={handleOpenDialog}
          >
            Add to cart
          </Button>
        </CardActions>
      ) : null}
      <Dialog open={openDialog} onClose={handleCloseDialog}>
        <DialogTitle>Enter Quantity</DialogTitle>
        <DialogContent>
          <DialogContentText>
            Enter the quantity of {item.itemName} you want to add to the cart:
          </DialogContentText>
          <br />
          <TextField
            autoFocus
            margin="dense"
            label="Quantity"
            type="number"
            fullWidth
            value={quantity}
            onChange={(e) => setQuantity(parseInt(e.target.value))}
          />
        </DialogContent>
        <DialogActions>
          <Button onClick={handleCloseDialog}>Cancel</Button>
          <Button onClick={handleAddToCart} color="primary">
            Add to Cart
          </Button>
        </DialogActions>
      </Dialog>
    </Card>
  );
};

export default ItemCard;
