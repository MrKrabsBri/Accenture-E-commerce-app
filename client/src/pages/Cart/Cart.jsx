import React, { useEffect, useState } from "react";
import { getCartItemsByUserId, getItemsByIds } from "../../services/api";
import Navbar from "../../components/Navbar";
import CartFooter from "../../components/CartFooter";
import { Typography, Container } from "@mui/material";
import CartList from "../../components/CartList";
const CartPage = () => {
  const [cartItems, setCartItems] = useState([]);
  const [detailedItems, setDetailedItems] = useState([]);
  const userId = JSON.parse(localStorage.getItem("authenticatedUser"))?.userId;
  useEffect(() => {
    const fetchCartItems = async () => {
      try {
        if (userId) {
          const cartData = await getCartItemsByUserId(userId);
          console.log("cartData:", cartData);
          setCartItems(cartData);
          const itemIds = cartData.map((item) => item.itemId);
          console.log("itemIds:", itemIds);

          const detailedItemsData = await getItemsByIds(itemIds);
          console.log("Detailed items:", detailedItemsData);
          setDetailedItems(detailedItemsData);
        }
      } catch (error) {
        console.error("Error fetching cart items:", error);
      }
    };
    fetchCartItems();
  }, [userId]);
  return (
    <div>
      <Navbar />
      <Container>
        <Typography variant="h4" align="center" pt={3}>
          Your cart
        </Typography>
        <CartList detailedItems={detailedItems} />
        <CartFooter detailedItems={detailedItems} />
      </Container>
    </div>
  );
};

export default CartPage;
