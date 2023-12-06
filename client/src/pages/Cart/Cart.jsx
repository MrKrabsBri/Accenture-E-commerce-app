import React from "react";
import Navbar from "../../components/Navbar";
import Cart from "../../components/Cart";
import CartFooter from "../../components/CartFooter";
import { Button, Typography, Container } from "@mui/material";
const CartPage = () => {
  return (
    <div>
      <Navbar />
      <Container>
        <Typography variant="h4" align="center" pt={3}>
          Your cart
        </Typography>
        <Cart />
        <CartFooter />
      </Container>
    </div>
  );
};

export default CartPage;
