import React from "react";
import Navbar from "../../components/Navbar";
import CartFooter from "../../components/CartFooter";
import { Typography, Container } from "@mui/material";
import CartList from "../../components/CartList";
const CartPage = () => {
  return (
    <div>
      <Navbar />
      <Container>
        <Typography variant="h4" align="center" pt={3}>
          Your cart
        </Typography>
        <CartList />
        <CartFooter />
      </Container>
    </div>
  );
};

export default CartPage;
