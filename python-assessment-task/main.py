import requests
import pandas as pd

url = 'https://dummyjson.com/products'
data = requests.get(url)

if data.status_code == 200:
    data = data.json()
    def calculate_final_price():
        for e in data['products']:
         calculate_discount = (e['price'] * e['discountPercentage'])/ 100
         final_price = e['price'] - calculate_discount
         message = "the product: {} and the final price is: {:.2f}".format(e['title'], final_price)
         print(format(message))
         
    def total_amount_of_products(total_products):
        for p in data['products']:
            calculate_discount = (p['price'] * p['discountPercentage'])/ 100
            final_price = p['price'] - calculate_discount
            if(final_price > 100):
                total_products = total_products + 1
        print("Total amount of products > 100 is {0}".format(total_products))

    def read_parquet(path_parquet):
        parquet_File = path_parquet
        parquet_data = pd.read_parquet(parquet_File, engine='auto', storage_options=None)
        for e in data['products']:
         calculate_discount = (e['price'] * e['discountPercentage'])/ 100
         final_price = e['price'] - calculate_discount
         final_price_format = "{:.2f}".format(final_price)
         for p in parquet_data.index:
            if(parquet_data['title'][p] == e['title'] and str(parquet_data['final_price'][p] == final_price_format)):
               print(parquet_data['title'][p] + "  " +str(parquet_data['final_price'][p]))
               break;
else:   
    codigo = data.status_code
    print(codigo)

#Execute the functions
calculate_final_price()
total_amount_of_products(0)
read_parquet('./data/product_prices_calculated.parquet')