import { StatusBar } from 'expo-status-bar';
import { Alert, StyleSheet, Text, Image, Pressable, View } from 'react-native';

export default function MealDeliveryScreen({navigation, route}) {

  return (

    <View style={styles.container}>

      <View style={{marginBottom: 10}}>
        <Pressable onPress={() => {
          Alert.alert(
            'Information',
            'Sorry this screen is not yet available.',
            [
              {text: 'OK', onPress: () => {}},
            ],
          );
        }}>
          <Image
            style={styles.link}
            source={require('../assets/pickups-available.jpg')}
          />
        </Pressable>
      </View>

      <View style={{marginBottom: 40}}>
        <Text style={styles.linkSubtitle} onPress={() => {
          Alert.alert(
            'Information',
            'Sorry this screen is not yet available.',
            [
              {text: 'OK', onPress: () => {}},
            ],
          );
        }}>
          Available Pickups (Acceptance)
        </Text>
      </View>

      <View style={{marginBottom: 10}}>
        <Pressable onPress={() => {
          Alert.alert(
            'Information',
            'Sorry this screen is not yet available.',
            [
              {text: 'OK', onPress: () => {}},
            ],
          );
        }}>
          <Image
            style={styles.link}
            source={require('../assets/pickups-current.jpg')}
          />
        </Pressable>
      </View>

      <View style={{marginBottom: 40}}>
        <Text style={styles.linkSubtitle} onPress={() => {
          Alert.alert(
            'Information',
            'Sorry this screen is not yet available.',
            [
              {text: 'OK', onPress: () => {}},
            ],
          );
        }}>
          Current Pickups
        </Text>
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

  link: {
    resizeMode: 'contain',
    width: 232,
    height: 128,
  },

  linkSubtitle: {
    color: '#000000',           // black
    fontSize: 17,
    fontStyle: 'normal',
  },

});