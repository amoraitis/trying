using namespace System.Net

# Input bindings are passed in via param block.
param($Request, $TriggerMetadata)

# Write to the Azure Functions log stream.
Write-Host "Serverless Dreidel!"

$randomDreidel = 1, 2, 3, 4 | Get-Random
$status = [HttpStatusCode]::OK;

switch ($randomDreidel) {

    1 {$body = "נ (Nun)"; break}
    2 {$body = "ג (Gimmel)"; break}
    3 {$body = "ה (Hay)"; break}
    4 {$body = "ש (Shin)"; break}
    Default {$status = [HttpStatusCode]::BadRequest;
        $body = "Failed Dreidel!"; break}
}

# Associate values to output bindings by calling 'Push-OutputBinding'.
Push-OutputBinding -Name Response -Value ([HttpResponseContext]@{
    StatusCode = $status
    Body = $body
})
