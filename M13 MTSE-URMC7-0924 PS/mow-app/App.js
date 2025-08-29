import { NavigationContainer }  from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';

import LoginScreen        from './screens/LoginScreen.js';
import HomeScreen         from './screens/HomeScreen.js';
import MealDeliveryScreen from './screens/MealDeliveryScreen.js';

export default function App() {

  const Stack = createStackNavigator();

  return (

    <NavigationContainer>

      <Stack.Navigator
        initialRouteName="Login"
        screenOptions={{
          headerStyle: {
            backgroundColor: '#add8e6', // light blue
          },
        }}>

        <Stack.Screen
          name="Login"
          component={LoginScreen}
          options={
            {headerShown: false}
          } />

        <Stack.Screen
          name="Home"
          component={HomeScreen}
          options={
            {headerShown: false}
          } />

        <Stack.Screen
          name="MealDelivery"
          component={MealDeliveryScreen}
          options={
            {title: 'Meal Delivery'}
          } />

      </Stack.Navigator>

    </NavigationContainer>

  );

}