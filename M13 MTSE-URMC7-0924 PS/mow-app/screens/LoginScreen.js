import { StatusBar } from 'expo-status-bar';
import { Alert, StyleSheet, Pressable, TextInput, Text, Image, View } from 'react-native';
import { useState } from 'react';

import axios from 'axios';

export default function LoginScreen({navigation}) {

  const [email, setEmail]       = useState('');

  const [password, setPassword] = useState('');

  const [message, setMessage]   = useState('');

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

      <View style={{marginBottom: 10}}>
        <TextInput
          style={styles.input}
          placeholder='Email Address'
          placeHolderTextColor='#808080' // gray
          value={email}
          onChangeText={(text) => setEmail(text)}
        />
      </View>

      <View style={{marginBottom: 10}}>
        <TextInput
          style={styles.input}
          placeholder='Password'
          placeHolderTextColor='#808080' // gray
          value={password}
          onChangeText={(text) => setPassword(text)}
          secureTextEntry={true}
        />
      </View>

      <View style={{marginBottom: 20}}>
        <Pressable style={styles.button} onPress={async () => {
          if (!email || !/\S+@\S+\.\S+/.test(email) || !password) {
            Alert.alert(
              'Error',
              'Please enter valid email address and password.',
              [
                {text: 'OK', onPress: () => {}},
              ],
            );
          } else {
           /************************************************************
            navigation.navigate('Home');
            ************************************************************/
            try {
              let response = await axios.post(
                'https://mow-app.azurewebsites.net:443/login/api',
                {
                  email: email,
                  password: password
                },
                {
                  headers: {
                    'content-type': 'application/json',
                    'accept':       'application/json'
                  }
                }
              );
              if (response.status === 200) {
                setMessage('');
                console.log(`Logged: ${response.data.profile.email}`);
                console.log(`Token:  ${response.data.token}`);
                navigation.navigate('Home', {
                  profile: response.data.profile,
                  token:   response.data.token,
                });
              } else {
                throw new Error(`${response.status} ${response.statusText}`);
              }
            } catch (error) {
              if (error.message === 'Request failed with status code 404') {
                setMessage(`Error: Matching profile not found`);
              } else {
                setMessage(`Error: ${error.message}`);
              }
            }
           /************************************************************/
          }
        }}>
          <Text style={styles.buttonTitle}>Login</Text>
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

  input: {
    borderColor: '#808080',     // gray
    borderWidth: 1,
    borderRadius: 5,
    fontSize: 17,
    minWidth: 300,
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
    fontSize: 18,
    textAlign: 'center',
  },

  message: {
    color: '#8b0000',           // dark red
    fontSize: 17,
    fontStyle: 'italic',
  },

});