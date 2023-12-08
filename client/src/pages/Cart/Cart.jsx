import React, { useEffect, useState } from "react";
import { getCartItemsByUserId, getItemsByIds } from "../../services/api";
import Navbar from "../../components/Navbar";
import CartList from "../../components/CartList";
import { Typography, Container } from "@mui/material";
import CartFooter from "../../components/CartFooter";
const CartPage = () => {
  const [cartItems, setCartItems] = useState([]);
  const [detailedItems, setDetailedItems] = useState([]);
  const userId = JSON.parse(localStorage.getItem("authenticatedUser"))?.userId;

  useEffect(() => {
    const fetchCartItems = async () => {
      try {
        if (userId) {
          const cartData = await getCartItemsByUserId(userId);
          setCartItems(cartData);
          const itemIds = cartData.map((item) => item.itemId);

          const detailedItemsData = await getItemsByIds(itemIds);
          setDetailedItems(detailedItemsData);
        }
      } catch (error) {
        console.error("Error fetching cart items:", error);
      }
    };
    fetchCartItems();
  }, [userId]);

  const removeFromCart = (itemIdToRemove) => {
    const updatedCartItems = cartItems.filter(
      (item) => item.itemId !== itemIdToRemove
    );
    const updatedItemIds = updatedCartItems.map((item) => item.itemId);
    const updatedDetailedItems = detailedItems.filter((item) =>
      updatedItemIds.includes(item.itemId)
    );

    setCartItems(updatedCartItems);
    setDetailedItems(updatedDetailedItems);
  };
  return (
    <div>
      <Navbar />
      <Container>
        <Typography variant="h4" align="center" pt={3}>
          Your cart
        </Typography>
        <CartList
          detailedItems={detailedItems}
          cartData={cartItems}
          removeFromCart={removeFromCart}
        />
        <CartFooter detailedItems={detailedItems} />
      </Container>
    </div>
  );
};

export default CartPage;
