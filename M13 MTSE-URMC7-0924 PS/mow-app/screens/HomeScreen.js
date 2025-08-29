import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Pressable, Text, Image, View } from 'react-native';
import { useState } from 'react';

export default function HomeScreen({navigation, route}) {

  const [message, setMessage] = useState('');

  return (

    <View style={styles.container}>

      <View style={{marginBottom: 10}}>
        <Image
          style={styles.logo}
          source={require('../assets/logo-mow.png')}
        />
      </View>

      <View style={{marginBottom: 40}}>
        <Text style={styles.logoSubtitle}>for MerryMeal</Text>
      </View>

      <View style={{marginBottom: 20}}>
        <Pressable style={styles.button} onPress={() => {
         /************************************************************
          navigation.navigate('MealDelivery');
          ************************************************************/
         if (route.params.profile.role == 'Partner - FSP' || route.params.profile.role == 'Volunteer - FSP') {
            setMessage('Information: Access denied for Meal Delivery');
         } else {
            setMessage('');
            navigation.navigate('MealDelivery', {
              profile: route.params.profile,
              token:   route.params.token,
            });
        }
         /************************************************************/
        }}>
          <Text style={styles.buttonTitle}>Meal Delivery</Text>
        </Pressable>
      </View>

      <View style={{marginBottom: 20}}>
        <Pressable style={styles.button} onPress={() => {
          setMessage('');
          navigation.navigate('Login');
        }}>
          <Text style={styles.buttonTitle}>Logout</Text>
        </Pressable>
      </View>

      <View style={{marginBottom: 20}}>
        <Text style={styles.message}>{message}</Text>
      </View>

      <StatusBar style="auto" />

    </View>

  );

}

const styles = StyleSheet.create({

  container: {
    backgroundColor: '#fff',    // white
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },

  logo: {
    resizeMode: 'contain',
    width: 97,
    height: 94,
  },

  logoSubtitle: {
    color: '#8b0000',           // dark red
    fontSize: 17,
    fontStyle: 'italic',
  },

  button: {
    backgroundColor: '#d3d3d3', // light gray
    width: 300,
    height: 40,
    borderRadius: 5,
    justifyContent: 'center',
  },

  buttonTitle: {
    color: '#000000',           // black
    fontSize: 17,
    textAlign: 'center',
  },

  message: {
    color: '#ff8c00',           // dark orange
    fontSize: 17,
    fontStyle: 'italic',
  },

});