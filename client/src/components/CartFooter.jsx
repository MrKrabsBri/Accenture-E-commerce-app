import React from "react";
import { Box, Button, Typography } from "@mui/material";
import ShoppingCartIcon from "@mui/icons-material/ShoppingCart";
import ArrowForwardIcon from "@mui/icons-material/ArrowForward";
import { useNavigate } from "react-router-dom";
const CartFooter = () => {
  const navigate = useNavigate();
  const handleAddMoreItems = () => {
    navigate("/");
  };

  return (
    <Box>
      <Box display="flex" justifyContent="center" alignItems="center" mb={2}>
        <Typography
          variant="h6"
          style={{ fontWeight: "bold", marginRight: "1rem" }}
        >
          Items in cart: 1
        </Typography>
        <Typography
          variant="h6"
          style={{ fontWeight: "bold", marginLeft: "1rem" }}
        >
          Total: $100
        </Typography>
      </Box>
      <Box display="flex" justifyContent="center">
        <Button
          variant="contained"
          color="success"
          startIcon={<ShoppingCartIcon />}
          style={{ marginRight: "1rem" }}
          onClick={handleAddMoreItems}
        >
          Add more items
        </Button>

        <Button
          variant="contained"
          color="primary"
          endIcon={<ArrowForwardIcon />}
          style={{ marginLeft: "1rem" }}
        >
          Checkout
        </Button>
      </Box>
    </Box>
  );
};

export default CartFooter;
